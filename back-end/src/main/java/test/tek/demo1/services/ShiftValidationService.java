package test.tek.demo1.services;

import org.springframework.stereotype.Service;
import test.tek.demo1.enums.ShiftStatusEnum;

import java.util.Optional;

@Service
public class ShiftValidationService {

    public Optional<ShiftStatusEnum> validateShitChangeAction(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        Optional<ShiftStatusEnum> validAction = Optional.empty();
        switch(futureShiftStatus) {
            case SHIFT_ACTIVE:
                validAction = canBeginShift(currentShiftStatus, futureShiftStatus);
                break;
            case SHIFT_END:
                validAction = canEndShift(currentShiftStatus, futureShiftStatus);
                break;
            case BREAK_START:
                validAction = canStartBreak(currentShiftStatus, futureShiftStatus);
                break;
            case BREAK_END:
                validAction = canEndBreak(currentShiftStatus, futureShiftStatus);
                break;
            case LUNCH_START:
                validAction = canStartLunch(currentShiftStatus, futureShiftStatus);
                break;
            case LUNCH_END:
                validAction = canEndLunch(currentShiftStatus, futureShiftStatus);
                break;
            default:
                break;
        }

        return validAction;
    }

    private Optional<ShiftStatusEnum> canBeginShift(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if((currentShiftStatus == ShiftStatusEnum.NEW_USER || currentShiftStatus == ShiftStatusEnum.SHIFT_END)
                && futureShiftStatus == ShiftStatusEnum.SHIFT_ACTIVE)
            return Optional.of(futureShiftStatus);
        return Optional.empty();
    }

    private Optional<ShiftStatusEnum> canEndShift(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if(currentShiftStatus == ShiftStatusEnum.SHIFT_ACTIVE && futureShiftStatus == ShiftStatusEnum.SHIFT_END)
            return Optional.of(futureShiftStatus);
        return Optional.empty();
    }

    private Optional<ShiftStatusEnum> canStartBreak(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if(currentShiftStatus == ShiftStatusEnum.SHIFT_ACTIVE && futureShiftStatus == ShiftStatusEnum.BREAK_START)
            return Optional.of(futureShiftStatus);
        return Optional.empty();
    }

    private Optional<ShiftStatusEnum> canEndBreak(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if(currentShiftStatus == ShiftStatusEnum.BREAK_START && futureShiftStatus == ShiftStatusEnum.BREAK_END)
            return Optional.of(ShiftStatusEnum.SHIFT_ACTIVE);
        return Optional.empty();
    }

    private Optional<ShiftStatusEnum> canStartLunch(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if(currentShiftStatus == ShiftStatusEnum.SHIFT_ACTIVE && futureShiftStatus == ShiftStatusEnum.LUNCH_START)
            return Optional.of(futureShiftStatus);
        return Optional.empty();
    }

    private Optional<ShiftStatusEnum> canEndLunch(ShiftStatusEnum currentShiftStatus, ShiftStatusEnum futureShiftStatus) {
        if(currentShiftStatus == ShiftStatusEnum.LUNCH_START && futureShiftStatus == ShiftStatusEnum.LUNCH_END)
            return Optional.of(ShiftStatusEnum.SHIFT_ACTIVE);
        return Optional.empty();
    }
}
