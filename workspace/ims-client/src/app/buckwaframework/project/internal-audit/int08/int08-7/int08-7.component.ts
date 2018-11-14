import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { ActivatedRoute } from '@angular/router';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { AuthService } from 'services/auth.service';


declare var $: any;

@Component({
  selector: 'app-int08-7',
  templateUrl: './int08-7.component.html',
  styleUrls: ['./int08-7.component.css']
})
export class Int087Component implements OnInit {
  @Output() discard = new EventEmitter<any>();
 
  breadcrumb: BreadCrumb[];
  obj: data;
  box1: Boolean;
  box2: Boolean;
 
  constructor( 
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private authService: AuthService,
    ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "รายงานผลการตรวจสอบรายได้", route: "#" },
    ];

    this.obj = new data();
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-087').then(user=>{
    //this.user= user;
   this.obj.officer= user.fullName;
   this.obj.position=user.title;
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
		form.action = AjaxService.CONTEXT_PATH + "internalAudit/report/pdf/int/reportCheckIncome";
    form.style.display = "none";    
    var jsonInput = document.createElement("input");
    jsonInput.name = "json";
    jsonInput.value = JSON.stringify(this.obj);
    form.appendChild(jsonInput);

    document.body.appendChild(form);
	  form.submit();


  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

}

class data {
  logo: string = "logo1.jpg";
  government : string;
  date : string;   
  study : string;
  billLost: string;
  sendMoney: string;
  group1Flag: string;
  group2Flag: string;
  officer:string;
  position:string;
} 

