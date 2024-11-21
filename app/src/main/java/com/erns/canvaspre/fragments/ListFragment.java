package com.erns.canvaspre.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erns.canvaspre.PictureAdapter;
import com.erns.canvaspre.R;
import com.erns.canvaspre.model.database.AppDatabase;
import com.erns.canvaspre.model.database.GalleryRepository;
import com.erns.canvaspre.model.ent.PictureEntity;

import java.util.List;
import java.util.concurrent.Executors;


public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private GalleryRepository galleryRepository;
    private PictureAdapter adapter;
    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Instancia del repositorio (asegúrate de pasarlo correctamente desde la actividad o inyectarlo)
        galleryRepository = new GalleryRepository(AppDatabase.getInstance(getContext()));

        // Cargar la lista de pinturas y configurar el adaptador

        Executors.newSingleThreadExecutor().execute(() -> {
                   List<PictureEntity> pictures = galleryRepository.getPictures();
                   if(!pictures.isEmpty()){
                       adapter = new PictureAdapter(pictures);
                       recyclerView.setAdapter(adapter);
                   }

        }
        );


        return view;
    }


}