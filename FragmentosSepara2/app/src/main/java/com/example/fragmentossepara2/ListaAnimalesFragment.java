package com.example.fragmentossepara2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaAnimalesFragment extends Fragment {
    private static String[] animales = new String[]{
            "Perro",
            "Gato",
            "Raton"

    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.lista_animales_fragment,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));

        ListaAnimalAdapter listaAnimalAdapter = new ListaAnimalAdapter(animales, getFragmentManager());
        recyclerView.setAdapter(listaAnimalAdapter);

        return view;
    }


    static class ListaAnimalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private String[] animales;
        private final FragmentManager fragmentManager;

        public ListaAnimalAdapter(String[] animales, FragmentManager fragmentManager){
            this.animales=animales;
            this.fragmentManager=fragmentManager;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
            View view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);


            return new RecyclerView.ViewHolder(view) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            final TextView textView = (TextView)holder.itemView;
            final String animal = animales[position];
            textView.setText(animal);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Context context = v.getContext();

                    int orientation = context.getResources().getConfiguration().orientation;

                    if(orientation== Configuration.ORIENTATION_PORTRAIT) {

                        Intent intent = new Intent(context, InfoAnimalActivity.class);
                        intent.putExtra("animal", animal);
                        context.startActivity(intent);

                    } else if(orientation==Configuration.ORIENTATION_LANDSCAPE) {

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        InfoAnimalFragment info_animal_fragmet = new InfoAnimalFragment();
                        Bundle args = new Bundle();

                        args.putString("animal",animal);

                        info_animal_fragmet.setArguments(args);

                        fragmentTransaction.replace(R.id.info_animal_land,info_animal_fragmet);
                        fragmentTransaction.commit();

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return animales.length;
        }
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,
                parent,false);
        return new RecyclerView.ViewHolder(view) {};

    }
}



