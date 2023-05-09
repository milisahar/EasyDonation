package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventType;
import com.example.easydonatemaster.repositories.EventRepositoy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class EventService implements IEventService{
private EventRepositoy eventRepositoy;
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public Event addEvent(Event event) {
        return eventRepositoy.save(event) ;
    }

    @Override
    public Event updateEvent(Event event, int id) {
        eventRepositoy.deleteById(id);
        return eventRepositoy.save(event);
    }

    @Override
    public List<Event> listEvent() {
        return eventRepositoy.findAll();
    }

    @Override
    public void deleteEvent(int id) {
        eventRepositoy.deleteById(id);

    }

    @Override
    public Event getEventById(int id) {
       return eventRepositoy.findById(id).get();
    }

    public void archiveEvents() {
        List<Event> events = eventRepositoy.findByOngoing(false);
        for (Event event : events) {
            eventRepositoy.delete(event);
        }
    }
    @Scheduled(cron = "*/10 * * * * *")
    public void rappelEvents () {
        SimpleMailMessage message = new SimpleMailMessage();
        String mail = "balkiss.ghanmi@esprit.tn";
        for (Event e : listEvent()
            ) {
            message.setTo(mail);
            message.setSubject("event");
            message.setText("L'evenement: " +e.getTitle()+" a "+ e.getTicketList().stream().count()+ "participations! ");
            mailSender.send(message);
            }
    }

    @Override
    public Long  NumberOfEvents( EventType eventType) {
        return eventRepositoy.findByEventType(eventType).stream().count();
    }

    @Override
    public List<Event> findByEventDate(Date eventDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(eventDate);
        List<Event> events =new ArrayList<>();
        List<Event> eventsdat =new ArrayList<>();
        events= eventRepositoy.findAll();
        for (Event event :events){
            SimpleDateFormat dateevent = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDateEvent = dateFormat.format( event.getEventDate());
            if(formattedDate.equals(formattedDateEvent)){
                eventsdat.add(event);
            }
        }

        return eventsdat;
    }

    @Override
    public List<Event> findByPlace(String place) {
        return eventRepositoy.findByPlace(place);
    }
}
