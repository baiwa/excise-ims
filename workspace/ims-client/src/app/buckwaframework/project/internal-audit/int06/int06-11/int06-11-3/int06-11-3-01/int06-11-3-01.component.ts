import { Component, OnInit, AfterViewInit } from '@angular/core';
import { int0611301Service } from './int06-11-3-01.service';
import { BreadCrumb } from 'models/breadcrumb';


declare var $: any;
@Component({
  selector: 'app-int06-11-3-01',
  templateUrl: './int06-11-3-01.component.html',
  styleUrls: ['./int06-11-3-01.component.css'],
  providers: [int0611301Service]
})
export class Int0611301Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "บันทึกคำขอ", route: "#" },
    { label: "ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร", route: "#" },
  ];

  // ==> disabled
  levelEducationDis1: boolean = true;
  typeSubjectDis1: boolean = true;
  bursaryDis1: boolean = true;

  // ==> params form
  form1: Form = new Form();
  form2: Form = new Form();
  form3: Form = new Form();

  // ==> params
  typeEducationList: any = [];
  subTypeEducationList: any = [];
  levelEducationList: any = [];
  typeSubjectList: any = [];
  bursaryList: any = [];
  number: any = [1, 2, 3];
  loading: boolean = false;


  money1 : any;
  constructor(
    private myService: int0611301Service,
  ) { }

  ngOnInit() {
    this.typeEducation();
    $('.ui.dropdown').dropdown();
    $(".number1").hide();

    console.log(this.levelEducationDis1);

  }
  ngAfterViewInit() {
    
  }

  showForm = (e) => {
    e.target.value == 1 ? $(".number1").show() : "";
  }

  onSubmit=(e)=>{    
    this.form1.typeSubject = $("#typeSubject").val();    

    this.checkMoneyForm1(this.form1);
  }

  checkMoneyForm1=(form)=>{
    this.myService.checkMoneyForm(form).then(res=>{
      this.money1 = res.value1;
      console.log(this.money1);
    });
  }

  typeEducation = () => {
    this.loading = true;
    this.myService.typeEducation().then((resolve) => {
      this.typeEducationList = resolve;
      this.loading = false;
    })
  }

  subTypeEducation = (event) => {
    $("#subTypeEducation").dropdown('restore defaults');
    $("#levelEducation").dropdown('restore defaults');
    $("#bursary").dropdown('restore defaults');
    this.subTypeEducationList = [];
    this.typeSubjectList = [];
    this.bursaryList = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.subTypeEducation(idMaster).then((resolve) => {
      this.subTypeEducationList = resolve;
      this.loading = false;
    });
  }

  levelEducation = (event) => {
    $("#levelEducation").dropdown('restore defaults');
    $("#bursary").dropdown('restore defaults');
    this.typeSubjectList = [];
    this.bursaryList = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.levelEducation(idMaster).then((resolve) => {
      this.levelEducationList = resolve;
      this.levelEducationDis1 = (this.levelEducationList.length == 0 ? true : false);
      this.loading = false;
    });
  }

  typeSubject = (event) => {
    $("#bursary").dropdown('restore defaults');
    this.bursaryList = [];
    this.loading = true;
    let idMaster = event.target.value;
    this.myService.typeSubject(idMaster).then((resolve) => {
      this.typeSubjectList = resolve;
      this.typeSubjectDis1 = (this.typeSubjectList.length == 0  ? true : false);      
      this.loading = false;
    });
  }

  bursary = (event) => {
    let idMaster = event.target.value;
    this.myService.bursary(idMaster).then((resolve) => {
      this.bursaryList = resolve;
      this.bursaryDis1 = (this.bursaryList.length == 0  ? true : false);      
      this.loading = false;
    });
  }

  showInput(req) {

  }
}

class Form {
  typeEducation: string = "";
  subTypeEducation: string = "";
  levelEducation: string = "";
  typeSubject: string = "";
  bursary: string = "";
}