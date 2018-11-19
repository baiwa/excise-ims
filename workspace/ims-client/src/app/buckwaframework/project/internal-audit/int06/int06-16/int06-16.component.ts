import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Int0616Service } from './int06-16.service';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';


declare var $: any;
@Component({
  selector: 'app-int06-16',
  templateUrl: './int06-16.component.html',
  styleUrls: ['./int06-16.component.css'],
  providers: [Int0616Service]
})
export class Int0616Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "รายงานการตรวจสอบ", route: "#" }
  ];

  obj: data;
  box1: Boolean;
  box2: Boolean;

  constructor(
    private ajax: AjaxService,
    private authService: AuthService,
    private myService: Int0616Service
  ) { 
    this.obj = new data();
  }

  ngOnInit() {

    this.authService.reRenderVersionProgram('INT-06-16').then(user=>{
     
      if (user.fullName) {
        this.obj.officer = user.fullName;
      }else{
        this.obj.officer = "";
      }
      if(user.title){
        this.obj.position=user.title;
      }else{
        this.obj.position="";
      }
      });

      this.calenda();
  }

  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date1"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('วดป'),
      onChanges: (date , text)=>{
       $("#date").val(text);
      }
    });

  }

  onSubmit = e => {
    this.obj.title="รายงานผลการตรวจสอบเบิกจ่าย"
    if(this.box1 && !this.box2){
      console.log("1,!2")
      this.obj.group1Flag="Y"
      this.obj.group2Flag="N"
    }else if(this.box2 && !this.box1){
      console.log("!1,2")
      this.obj.group1Flag="N"
      this.obj.group2Flag="Y"
    }else if(this.box1 && this.box2){
      console.log("1,2")
      this.obj.group1Flag="Y"
      this.obj.group2Flag="Y"
    }else{
      console.log("!1,!2")
      this.obj.group1Flag="N"
      this.obj.group2Flag="N"
    }


    console.log(this.obj);
    this.obj.date =   $("#date").val();
    var form = document.createElement("form");
		form.method = "POST";
		form.action = AjaxService.CONTEXT_PATH + "internalAudit/report/pdf/int/reportCheckDisburse";
    form.style.display = "none";
    form.target = "_blank"       
    var jsonInput = document.createElement("input");
    jsonInput.name = "json";
    jsonInput.value = JSON.stringify(this.obj);
    form.appendChild(jsonInput);

    document.body.appendChild(form);
	  form.submit();


  }


  ngAfterViewInit() {
    this.callDatatable1();
    this.callDatatable2();
  }
  callDatatable1 = () => {
    this.myService.callDatatable1();
  }
  callDatatable2 = () => {
    this.myService.callDatatable2();
  }


  
}


class data {
  logo: string = "logo1.jpg";
  title:string ;
  government : string;
  date : string;   
  study : string;
  billLost: string;
  sendMoney: string;
  group1Flag: string;
  group2Flag: string;
  officer:string;
  position:string;
  documentNo:string;
} 

