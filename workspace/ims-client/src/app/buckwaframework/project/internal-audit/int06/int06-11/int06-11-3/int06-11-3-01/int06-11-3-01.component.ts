import { Component, OnInit, AfterViewInit } from '@angular/core';
import { int0611301Service } from './int06-11-3-01.service';
import { BreadCrumb } from 'models/breadcrumb';
import { IaService } from 'services/ia.service';
import { Route, Router } from '@angular/router';
import { async } from 'q';
import { Utils } from 'helpers/utils';



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
    { label: "บันทึกคำขอเบิก", route: "#" },
    { label: "บันทึกคำขอเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)", route: "#" },
  ];

  // ==> disabled
  levelEducationDis1: boolean = true;
  typeSubjectDis1: boolean = true;
  bursaryDis1: boolean = true;

  levelEducationDis2: boolean = true;
  typeSubjectDis2: boolean = true;
  bursaryDis2: boolean = true;

  levelEducationDis3: boolean = true;
  typeSubjectDis3: boolean = true;
  bursaryDis3: boolean = true;
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

  typeEducationList2: any = [];
  subTypeEducationList2: any = [];
  levelEducationList2: any = [];
  typeSubjectList2: any = [];
  bursaryList2: any = [];

  typeEducationList3: any = [];
  subTypeEducationList3: any = [];
  levelEducationList3: any = [];
  typeSubjectList3: any = [];
  bursaryList3: any = [];

  number: any = [1, 2, 3];
  loading: boolean = false;


  money1: any;
  money2: any;
  money3: any;

  objMoney: any = {
    "money1": this.money1,
    "money2": this.money2,
    "money3": this.money3,
  }

  nextButton: boolean = true;
  constructor(
    private myService: int0611301Service,
    private iaService: IaService,
    private router: Router
  ) { }

  ngOnInit() {
    this.typeEducation();
    this.typeEducation2();
    this.typeEducation3();
    $('.ui.dropdown').dropdown();
    $(".number1").hide();
    $(".number2").hide();
    $(".number3").hide();

    console.log(this.levelEducationDis1);

  }
  ngAfterViewInit() {

  }

  showForm = (e) => {
    let number = e.target.value;
    this.nextButton = false;
    if (number == 1) {
      $(".number1").show()
    }
    if (number == 2) {
      $(".number1").show();
      $(".number2").show();
    }
    if (number == 3) {
      $(".number1").show();
      $(".number2").show();
      $(".number3").show();
    }

  }

  onSubmit = (e) => {
    this.myService.checkMoney(this.form1, this.form2, this.form3).then((res) => {
      console.log("res : ", res);
      this.iaService.setData(res);
      this.router.navigate(['/int06/11/3']);
    });

  }

  claer = () => {    
    $(".number1").hide();
    $(".number2").hide();
    $(".number3").hide();
    $('.ui.dropdown').dropdown('restore defaults');
    this.nextButton = true;
  }
  /// ==> form1

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
      this.typeSubjectDis1 = (this.typeSubjectList.length == 0 ? true : false);
      this.loading = false;
    });
  }

  bursary = (event) => {
    let idMaster = event.target.value;
    this.myService.bursary(idMaster).then((resolve) => {
      this.bursaryList = resolve;
      this.bursaryDis1 = (this.bursaryList.length == 0 ? true : false);
      this.loading = false;
    });
  }
  /// ==> End form1
  /// ==> form2

  typeEducation2 = () => {
    this.loading = true;
    this.myService.typeEducation().then((resolve) => {
      this.typeEducationList2 = resolve;
      this.loading = false;
    })
  }

  subTypeEducation2 = (event) => {
    $("#subTypeEducation2").dropdown('restore defaults');
    $("#levelEducation2").dropdown('restore defaults');
    $("#bursary2").dropdown('restore defaults');
    this.subTypeEducationList2 = [];
    this.typeSubjectList2 = [];
    this.bursaryList2 = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.subTypeEducation(idMaster).then((resolve) => {
      this.subTypeEducationList2 = resolve;
      this.loading = false;
    });
  }

  levelEducation2 = (event) => {
    $("#levelEducation2").dropdown('restore defaults');
    $("#bursary2").dropdown('restore defaults');
    this.typeSubjectList2 = [];
    this.bursaryList2 = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.levelEducation(idMaster).then((resolve) => {
      this.levelEducationList2 = resolve;
      this.levelEducationDis2 = (this.levelEducationList2.length == 0 ? true : false);
      this.loading = false;
    });
  }

  typeSubject2 = (event) => {
    $("#bursary2").dropdown('restore defaults');
    this.bursaryList2 = [];
    this.loading = true;
    let idMaster = event.target.value;
    this.myService.typeSubject(idMaster).then((resolve) => {
      this.typeSubjectList2 = resolve;
      this.typeSubjectDis2 = (this.typeSubjectList2.length == 0 ? true : false);
      this.loading = false;
    });
  }

  bursary2 = (event) => {
    let idMaster = event.target.value;
    this.myService.bursary(idMaster).then((resolve) => {
      this.bursaryList2 = resolve;
      this.bursaryDis2 = (this.bursaryList2.length == 0 ? true : false);
      this.loading = false;
    });
  }
  /// ==> End form2

  /// ==> form3

  typeEducation3 = () => {
    this.loading = true;
    this.myService.typeEducation().then((resolve) => {
      this.typeEducationList3 = resolve;
      this.loading = false;
    })
  }

  subTypeEducation3 = (event) => {
    $("#subTypeEducation3").dropdown('restore defaults');
    $("#levelEducation3").dropdown('restore defaults');
    $("#bursary3").dropdown('restore defaults');
    this.subTypeEducationList3 = [];
    this.typeSubjectList3 = [];
    this.bursaryList3 = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.subTypeEducation(idMaster).then((resolve) => {
      this.subTypeEducationList3 = resolve;
      this.loading = false;
    });
  }

  levelEducation3 = (event) => {
    $("#levelEducation3").dropdown('restore defaults');
    $("#bursary3").dropdown('restore defaults');
    this.typeSubjectList3 = [];
    this.bursaryList3 = [];

    this.loading = true;
    let idMaster = event.target.value;
    this.myService.levelEducation(idMaster).then((resolve) => {
      this.levelEducationList3 = resolve;
      this.levelEducationDis3 = (this.levelEducationList3.length == 0 ? true : false);
      this.loading = false;
    });
  }

  typeSubject3 = (event) => {
    $("#bursary3").dropdown('restore defaults');
    this.bursaryList3 = [];
    this.loading = true;
    let idMaster = event.target.value;
    this.myService.typeSubject(idMaster).then((resolve) => {
      this.typeSubjectList3 = resolve;
      this.typeSubjectDis3 = (this.typeSubjectList3.length == 0 ? true : false);
      this.loading = false;
    });
  }

  bursary3 = (event) => {
    let idMaster = event.target.value;
    this.myService.bursary(idMaster).then((resolve) => {
      this.bursaryList3 = resolve;
      this.bursaryDis3 = (this.bursaryList3.length == 0 ? true : false);
      this.loading = false;
    });
  }
  /// ==> End form3
}

class Form {
  typeEducation: string = "";
  subTypeEducation: string = "";
  levelEducation: string = "";
  typeSubject: string = "";
  bursary: string = "";
}