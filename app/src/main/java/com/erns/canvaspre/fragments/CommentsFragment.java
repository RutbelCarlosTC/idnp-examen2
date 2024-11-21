package com.erns.canvaspre.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erns.canvaspre.R;
import com.erns.canvaspre.model.database.AppDatabase;
import com.erns.canvaspre.model.database.GalleryRepository;
import com.erns.canvaspre.model.ent.CommentEntity;
import com.erns.canvaspre.utils.ExecuteTask;

import java.util.ArrayList;
import java.util.List;

public class CommentsFragment extends Fragment {
    private RecyclerView recyclerView;
    private CommentsAdapter adapter;
    private List<CommentEntity> comments = new ArrayList<>();
    private GalleryRepository repository;
    private int pictureId;

    public static CommentsFragment newInstance(int pictureId) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt("PICTURE_ID", pictureId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pictureId = getArguments().getInt("PICTURE_ID");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvComments);
        adapter = new CommentsAdapter(getContext(), comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        repository = new GalleryRepository(AppDatabase.getInstance(requireContext()));

        // Botón cerrar
        view.findViewById(R.id.btnCloseComments).setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        // Cargar comentarios
        loadComments();

        // Agregar comentario
        view.findViewById(R.id.btnSubmitComment).setOnClickListener(v -> {
            String name = ((EditText) view.findViewById(R.id.etName)).getText().toString();
            String comment = ((EditText) view.findViewById(R.id.etComment)).getText().toString();
            int rating = Integer.parseInt(((Spinner) view.findViewById(R.id.spRating)).getSelectedItem().toString());

            if (!name.isEmpty() && !comment.isEmpty()) {
                addComment(new CommentEntity(pictureId, name, comment, rating));
            }
        });
    }

    private void loadComments() {
        ExecuteTask task = new ExecuteTask();
        task.asyncTask(() -> {
            comments.clear();
            comments.addAll(repository.getCommentsByPictureId(pictureId));
            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        });
    }

    private void addComment(CommentEntity comment) {
        ExecuteTask task = new ExecuteTask();
        task.asyncTask(() -> {
            repository.addComment(comment);
            loadComments(); // Recargar lista
        });
    }
}
