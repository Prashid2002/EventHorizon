package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private Context context;
    private List<BookingModel> ticketList;

    public TicketAdapter(Context context, List<BookingModel> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_item, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        BookingModel ticket = ticketList.get(position);

        holder.eventTitle.setText(ticket.getEventTitle());
        holder.eventVenue.setText(ticket.getEventVenue());
        holder.eventDateTime.setText(ticket.getEventTimeAndDate());
        holder.eventPrice.setText("Price: " + ticket.getEventPrice());
        holder.userName.setText("Booked by: " + ticket.getFullName());
        holder.userEmail.setText("Email: " + ticket.getEmail());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle, eventVenue, eventDateTime, eventPrice, userName, userEmail;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.ticket_event_title);
            eventVenue = itemView.findViewById(R.id.ticket_event_venue);
            eventDateTime = itemView.findViewById(R.id.ticket_event_date_time);
            eventPrice = itemView.findViewById(R.id.ticket_event_price);
            userName = itemView.findViewById(R.id.ticket_user_name);
            userEmail = itemView.findViewById(R.id.ticket_user_email);
        }
    }
}
