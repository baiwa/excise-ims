import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";

declare var $: any;

@Injectable()
export class Int065Service {

  constructor(
    private ajax: AjaxService,
    private message: MessageBarService
  ) {
    // TODO
  }

  sector = ():Promise<any> => {
    let url = "ia/int065/sector";
   
    return  new Promise((resolve, reject) => {
      this.ajax.get(url,res=>{
        resolve(res.json())
    })});
  }
  area = (e) => {
    let url = "ia/int065/area";   
    return  new Promise((resolve, reject) => {
      this.ajax.post(url,JSON.stringify(e),res=>{
        resolve(res.json())
    })});
  }
  branch = (e) => {
    let url = "ia/int065/branch";   
    return  new Promise((resolve, reject) => {
      this.ajax.post(url,JSON.stringify(e),res=>{
        resolve(res.json())
    })});
  }
  dataTable = () => {
    $("#dataTable").DataTable();
  }
  
}
