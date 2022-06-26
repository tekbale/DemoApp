export interface UserDto {
    userId: string,
    shiftStatus: ShiftStatus
};

export interface ShiftDataDto {
    index: string,
    userId: string,
    shiftStatus: ShiftStatus,
    shiftRecordTime: Date
}

export interface UserValidityDto {
    userId: string,
    isValidUser: boolean
}

export enum ShiftStatus {
    NEW_USER,
    SHIFT_ACTIVE,
    SHIFT_END,
    BREAK_START,
    BREAK_END,
    LUNCH_START,
    LUNCH_END
};