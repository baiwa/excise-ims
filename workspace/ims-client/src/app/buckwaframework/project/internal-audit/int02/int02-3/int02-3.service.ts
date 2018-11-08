import { Injectable } from '@angular/core';
import { AjaxService } from 'services/index';
import { Headers } from '@angular/http';
import { toFormData } from 'helpers/index';


const URL = {
    SAVE: "ia/int02/save_qtn_report_detail",
    ORIGIN: "ia/int02/questionnaire_detail/datatable",
    NEWERS: "ia/int02/qtn_report_detail_by_hdr_id/datatable"
}

@Injectable()
export class Int023Service {

    constructor(private ajax: AjaxService) { }

    getOrigins(code: string): Promise<Int023Vo<QuestionaireMinor>[]> {
        return this.ajax.post(URL.ORIGIN, toFormData({ draw: 1, start: 0, length: 0, headerCode: code }), response => {
            const data = response.json().data as Int023Vo<QuestionaireMinor>[];
            console.log("ORIGINS =>", data);
            return data;
        }, error => {
            console.error("ERROR[getOrigins] =>", error);
            return [];
        }, new Headers());
    }

    getNewers(id: string): Promise<Int023Vo<QtnReportDetail>[]> {
        return this.ajax.post(`${URL.NEWERS}/${id}`, toFormData({ draw: 1, start: 0, length: 0 }), response => {
            const data = response.json().data as Int023Vo<QtnReportDetail>[];
            console.log("NEWERS =>", data);
            return data;
        }, error => {
            console.error("ERROR[getNewers] =>", error);
            return [];
        }, new Headers());
    }

}


export interface Int023Vo<T> {
    qtnReportHdrId: number;
    qtnReportManId: number;
    qtnMainDetail: string;
    detail: T[];
}

export interface QuestionaireMinor {
    qtnMinorId: number;
    mainId: number;
    headerCode: string;
    qtnMinorDetail: string;
}

export interface QtnReportDetail {
    qtnReportDtlId: number;
    qtnReportManId: number;
    qtnMainDetail: string;
}