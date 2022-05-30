package com.example.easydrug.viewholder;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.model.DrugInteraction;

public class InteractionViewHolder extends RecyclerView.ViewHolder {
    TextView drugName;
    TextView description;
    TextView possibilities;
    private DrugInteraction interaction;

    public InteractionViewHolder(View itemView) {
        super(itemView);
        drugName = itemView.findViewById(R.id.drug_name);
        description = itemView.findViewById(R.id.interaction_description);
        possibilities = itemView.findViewById(R.id.side_effect_detail);
    }

    public void setData(DrugInteraction interaction) {
        this.interaction = interaction;
        drugName.setText(interaction.getDrugName());
        description.setText(interaction.getInteractionDesc());
        
    }
}