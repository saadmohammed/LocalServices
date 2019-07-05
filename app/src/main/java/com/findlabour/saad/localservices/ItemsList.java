package com.findlabour.saad.localservices;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findlabour.saad.localservices.Interface.ItemClickListener;
import com.findlabour.saad.localservices.Model.Items;
import com.findlabour.saad.localservices.ViewHolder.ItemsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabaseItem;
    DatabaseReference databaseReferenceItem;

    //Add To Cart
    private Button buttonAdd;

    public Items currentItems;

    // Creating RecyclerView.
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter recyclerAdapter ;

    String categoryId="";

    FirebaseRecyclerAdapter<Items, ItemsViewHolder> firebaseRecyclerAdapter;

    //Search Box
    FirebaseRecyclerAdapter<Items, ItemsViewHolder> searchFirebaseRecyclerAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        firebaseDatabaseItem = FirebaseDatabase.getInstance();
        databaseReferenceItem = firebaseDatabaseItem.getReference().child("Items");

        recyclerView = findViewById(R.id.recycler_items_list);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //Getting from second page
        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadItemList(categoryId);
        }


        //Search Box
        materialSearchBar = findViewById(R.id.item_search);
        materialSearchBar.setHint("Search 1000+ products");
        materialSearchBar.setSpeechMode(true);
        loadSuggest();


        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(5);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //When the user type , we suggest search list
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList)// loop in suggest list
                {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //when search bar is close
                //Restore original suggest dapter
                if(!enabled)
                    recyclerView.setAdapter(firebaseRecyclerAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //When Search Finish
                //Show result of search adapter
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    private void startSearch(CharSequence text) {

        Query query = FirebaseDatabase.getInstance().getReference().child("Items").orderByChild("Name").equalTo(text.toString());//compare name
        FirebaseRecyclerOptions<Items> options = new FirebaseRecyclerOptions.Builder<Items>().setQuery(query, Items.class).build();
        searchFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Items, ItemsViewHolder>(options){
            @NonNull
            @Override
            public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list_item, viewGroup, false);
                return  new ItemsViewHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull Items model) {
                holder.textItemName.setText(model.getName());
                holder.textItemPrice.setText(model.getPrice());
                holder.textItemQuantity.setText(model.getQuantity());
                Picasso.get().load(model.getImage()).into(holder.imageItem);

                final Items clickCategoryItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Because category Id is key, so we just get key of
                        Toast.makeText(getApplicationContext(),""+clickCategoryItem.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } ;
        recyclerView.setAdapter(searchFirebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    private void loadSuggest() {
        databaseReferenceItem.orderByChild("CategoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                            Items items = postSnapshot.getValue(Items.class);
                            suggestList.add(items.getName());// Add name of item to suggest list
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void loadItemList(String categoryId) {

        Query query = FirebaseDatabase.getInstance().getReference().child("Items").orderByChild("CategoryId").equalTo(categoryId);
        FirebaseRecyclerOptions<Items> options = new FirebaseRecyclerOptions.Builder<Items>().setQuery(query, Items.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Items, ItemsViewHolder>(options){
            @NonNull
            @Override
            public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list_item, viewGroup, false);
                return  new ItemsViewHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull Items model) {
                holder.textItemName.setText(model.getName());
                holder.textItemPrice.setText(model.getPrice());
                holder.textItemQuantity.setText(model.getQuantity());
                holder.textItemDiscount.setText(model.getDiscount());
                Picasso.get().load(model.getImage()).into(holder.imageItem);

                final Items clickCategoryItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Because category Id is key, so we just get key of
                        /*Intent itemDetail = new Intent(getApplicationContext(), ItemDetails.class);
                        itemDetail.putExtra("CategoryId",firebaseRecyclerAdapter.getRef(position).getKey());
                        startActivity(itemDetail);*/

                        //Toast.makeText(getApplicationContext(),""+clickCategoryItem.getName()+" Add to cart" ,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } ;
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
}