package test.tek.demo1.db.entity;

import lombok.Data;
import test.tek.demo1.enums.ShiftStatusEnum;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_shift_data")
public class ShiftData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index")
    private Long index;

    @Column(name = "userId")
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    private ShiftStatusEnum shiftStatus;

    @Column(name="created_on")
    private Timestamp createdOn;
}
