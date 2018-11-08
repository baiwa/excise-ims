import { Injectable } from '@angular/core';
import { AjaxService, MessageBarService } from 'services/index';
import { Message } from 'models/index';

const URL = {
  GET: "ia/int061300/findAll",
  UPDATE: "ia/int061300/update",
  ADMIN:"ia/int061300/findAdministrativePosition",
	DIRECTOR:"ia/int061300/findDirectorPosition",
	ACADEMIC:"ia/int061300/findAcademicPosition",
	GENERAL:"ia/int061300/findGeneralPosition"

}

@Injectable()
export class Int0613Service {

  constructor(
    private ajax: AjaxService,
    private message: MessageBarService
  ) { }

  getRent(): Promise<RentHouseRule[]> {

    return this.ajax.get(URL.GET, response => {
      return response.json() as RentHouseRule[];
    });

  }

  updateRent(data: RentHouseRule[]): void {

    // binding data
    const request_data: RentHouseRuleVo = { data: data }

    // process TODO
    this.ajax.post(URL.UPDATE, request_data, response => {
      const msg = response.json() as Message;
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh, "สำเร็จ");
      } else {
        this.message.errorModal(msg.messageTh, "แจ้งเตือน");
      }
    }).catch(() => {
      this.message.errorModal("ไม่สามารถบันทึกการทำรายการได้ กรูณาทำรายการใหม่อีกครั้ง", "แจ้งเตือน");
    });

  }


  getAdministrative(): Promise<RentHouseRule[]> {

    return this.ajax.get(URL.ADMIN, response => {
      return response.json() as RentHouseRule[];
    });
    
  }

  getDirector(): Promise<RentHouseRule[]> {

    return this.ajax.get(URL.DIRECTOR, response => {
      return response.json() as RentHouseRule[];
    });
    
  }

  getAcademic(): Promise<RentHouseRule[]> {

    return this.ajax.get(URL.ACADEMIC, response => {
      return response.json() as RentHouseRule[];
    });
    
  }

  getGeneral(): Promise<RentHouseRule[]> {

    return this.ajax.get(URL.GENERAL, response => {
      return response.json() as RentHouseRule[];
    });
    
  }


  



}
export interface RentHouseRule {
  ruleId: number;
  year: string;
  copyNumber: string;
  position: string;
  codePosition: string;
  levels: string;
  salaryFrom: number;
  salaryTo: number;
  rentAmount: number;

}

export interface RentHouseRuleVo {
  data: RentHouseRule[];
}

/*

 result = response.json() as RentHouseRule[];
 result = [
  0: { ... },
  1: { ... },
  2: { ... },
  { ruleId: number, year: string, copyNumber: string, position: string, level: string, salaryFrom: number, salaryTo: number, rentAmount: number },
  { ruleId: number, year: string, copyNumber: string, position: string, level: string, salaryFrom: number, salaryTo: number, rentAmount: number },
 ]

*/