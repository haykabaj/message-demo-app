package hayk.abajyan.apps.am.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "messages")
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "receiver_id")
    private int receiverId;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", message='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
