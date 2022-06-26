package test.tek.demo1.db.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import test.tek.demo1.enums.ShiftStatusEnum;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    private ShiftStatusEnum shiftStatus;

    @Column(name="created_on")
    private Timestamp createdOn;

    @Column(name="updated_on")
    @CreationTimestamp
    private Timestamp udpatedOn;
}
