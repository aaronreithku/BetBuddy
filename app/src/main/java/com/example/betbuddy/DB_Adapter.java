package com.example.betbuddy;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DB_Adapter extends RecyclerView.Adapter<DB_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList sportsbook_id, team1_id, team2_id, betType_id, odds_id, amountBet_id, amountWon_id, won_id;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public DB_Adapter(Context context, ArrayList sportsbook_id, ArrayList team1_id, ArrayList team2_id, ArrayList betType_id, ArrayList odds_id, ArrayList amountBet_id, ArrayList amountWon_id, ArrayList won_id, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.sportsbook_id = sportsbook_id;
        this.team1_id = team1_id;
        this.team2_id = team2_id;
        this.betType_id = betType_id;
        this.odds_id = odds_id;
        this.amountBet_id = amountBet_id;
        this.amountWon_id = amountWon_id;
        this.won_id = won_id;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bet_entry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sportsbook_id.setText(String.valueOf(sportsbook_id.get(position)));
        holder.team1_id.setText(String.valueOf(team1_id.get(position)));
        holder.team2_id.setText(String.valueOf(team2_id.get(position)));
        holder.betType_id.setText(String.valueOf(betType_id.get(position)));
        holder.odds_id.setText(String.valueOf(odds_id.get(position)));
        holder.amountBet_id.setText(String.valueOf(amountBet_id.get(position)));
        holder.amountWon_id.setText(String.valueOf(amountWon_id.get(position)));
        holder.won_id.setText(String.valueOf(won_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return sportsbook_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sportsbook_id, team1_id, team2_id, betType_id, odds_id, amountBet_id, amountWon_id, won_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sportsbook_id = itemView.findViewById(R.id.text_sportsbook);
            team1_id = itemView.findViewById(R.id.text_team1);
            team2_id = itemView.findViewById(R.id.text_team2);
            betType_id = itemView.findViewById(R.id.text_betType);
            odds_id = itemView.findViewById(R.id.text_odds);
            amountBet_id = itemView.findViewById(R.id.text_amountBet);
            amountWon_id = itemView.findViewById(R.id.text_amountWon);
            won_id = itemView.findViewById(R.id.text_won);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
