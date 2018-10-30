export interface QtnMaster {
    qtnMasterId: number;
    qtnName: string;
    qtnYear: string;
    qtnFinished?: string;
    qtnSector: string;
    qtnStart?: Date;
    qtnEnd?: Date;
    isDeleted?: string;
}

export interface QtnTimeAlert {
    qtnAlertId: number;
    qtnAlertTime: Date;
    qtnTimes: number;
    status: string;
    qtnMasterId: number;
}

export interface Int022FormVo extends QtnMaster {
    alerts: QtnTimeAlert[];
}