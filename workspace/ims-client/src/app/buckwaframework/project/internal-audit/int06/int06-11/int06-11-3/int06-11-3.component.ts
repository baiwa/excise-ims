import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { FormGroup, Validators, FormBuilder, FormArray } from '@angular/forms';
import { Int06113Service } from './int06-11-3.service';

@Component({
  selector: 'app-int06-11-3',
  templateUrl: './int06-11-3.component.html',
  styleUrls: ['./int06-11-3.component.css'],
  providers: [Int06113Service]
})
export class Int06113Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "บันทึกคำขอ", route: "/int06/11/3-1" },
    { label: "ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร", route: "#" },
  ];

  // ===> params
  formControl: FormGroup;
  items : any = [];

  text : any = {
    mateDescription1 : "ไม่เป็นข้าราชการประจำหรือลูกจ้างประจำ",
    mateDescription2 : "เป็นข้าราชการ",
    mateDescription3 : "ลูกจ้างประจำ",
    mateDescription4 : "เป็นพนักงานหรือลูกจ้างใน รัฐวิสาหกิจ/หน่วยงานของทางราชการ ราชการส่วนท้องถิ่น กรุงเทพมหานคร องค์กรอิสระ องค์กรมหาชน หรือหน่วยงานอื่นใด",
  }

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private myService: Int06113Service
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06113');
    this.newFormControl();
    this.items = this.formControl.get('items') as FormArray;
    this.items.push(this.createChlidItem());
    this.items.push(this.createChlidItem());
  }
  ngAfterViewInit() {

  }
  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      //==> 1
      name: [""],
      pition: [""],
      belong: [""],
      //==> 2
      mate: [""],
      mateDescription: [""],
      pitionMate: [""],
      belongMate: [""],
      //==> 3
      status: [""],
      //==> 4
      type: [""],
      typeRecive: [""],
      typeReciveAmount: [""],
      offer: [""],
      offerType: [""],

      items: this.formBuilder.array([this.createChlidItem()])
    });
  }
  createChlidItem(): FormGroup {
    return this.formBuilder.group({
      name: '',
      birth: '',
      orderFather: '',
      orderMather: '',
      orderReplace: '',
      nameReplace: '',
      birthReplace: '',
      dateDeadReplace: '',
      education: '',
      educationProvince: '',
      educationDistrict: '',
      educationClass: '',
      amount: ''
    });
  }
  get f() {
    return this.formControl.controls;
  }

  save(event: any) {
    console.log(this.formControl.controls);
  }

}
