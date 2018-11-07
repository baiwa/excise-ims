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

  valueRadio : any = {
    mateDescription1 : "ไม่เป็นข้าราชการประจำหรือลูกจ้างประจำ",
    mateDescription2 : "เป็นข้าราชการ",
    mateDescription3 : "ลูกจ้างประจำ",
    mateDescription4 : "เป็นพนักงานหรือลูกจ้างใน รัฐวิสาหกิจ/หน่วยงานของทางราชการ ราชการส่วนท้องถิ่น กรุงเทพมหานคร องค์กรอิสระ องค์กรมหาชน หรือหน่วยงานอื่นใด",

    status1 : "เป็นบิดาชอบด้วยกฎหมาย",
    statsu2 : "เป็นมารดา",

    type1 : "เงินบำรุงการศึกษา",
    type2 : "เงินค่าเล่าเรียน",

    typeRecive1 : "ตามสิทธิ",
    typeRecive2 : "เฉพาะส่วนที่ยังขาดจากสิทธิ เป็นเงิน",

    offerType1 : "ข้าพเจ้ามีสิทธิได้รับเงินช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตรและข้อความที่ระบุข้างต้นเป็นความจริง",
    offerType2 : "บุตรของข้าพเจ้าอยู่ในข่ายได้รับการช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตร",
    offerType3 : "เป็นผู้ใช้สิทธิเบิกเงินช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตร แต่เพียงฝ่ายเดียว",
    offerType4 : "คู่สมรสของข้าพเจ้าได้รับการช่วยเหลือจากรัฐวิสาหกิจ หน่วยงานของทางราชการ ราชการท้องถิ่นกรุงเทพมหานครองค์กรอิสระ องค์การมหาชน หรือหน่วยงานอื่นใด ต่า กว่าจา นวนที่ได้รับจากทางราชการ"
  }

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private myService: Int06113Service
  ) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06113');
    this.newFormControl();
    this.items = this.formControl.get('items') as FormArray;
    // this.items.push(this.createChlidItem());
    // this.items.push(this.createChlidItem());
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
      sumAmount : [""],

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
    event.preventDefault();
    this.checkMateDescription(this.formControl.controls.mateDescription.value);
    this.checkStatus(this.formControl.controls.status.value);
    this.checkType(this.formControl.controls.type.value);
    this.checkTypeRecive(this.formControl.controls.typeRecive.value);
    this.checkOfferType(this.formControl.controls.offerType.value);
    
    this.myService.save(this.formControl.value);
  }

  checkMateDescription(checkMateDescription){
    if(checkMateDescription == 1){
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription1);
      return;
    }
    if(checkMateDescription == 2){
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription2);
      return;
    }
    if(checkMateDescription == 3){
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription3);
      return;
    }
    if(checkMateDescription == 4){
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription4);
      return;
    }
  }
  checkStatus(statsu){
    if(statsu == 1){
      this.formControl.controls.status.setValue(this.valueRadio.status1);
      return;
    }
    if(statsu == 2){
      this.formControl.controls.status.setValue(this.valueRadio.status2);
      return;
    }
  }

  checkType(type){
      if(type == 1){
        this.formControl.controls.type.setValue(this.valueRadio.type1);
        return;
      }
      if(type == 2){
        this.formControl.controls.type.setValue(this.valueRadio.type2);
        return;
      }
  }
  checkTypeRecive(typeRecive){
      if(typeRecive == 1){
        this.formControl.controls.typeRecive.setValue(this.valueRadio.typeRecive1);
        return;
      }
      if(typeRecive == 2){
        this.formControl.controls.typeRecive.setValue(this.valueRadio.typeRecive2);
        return;
      }
  }

  checkOfferType(offerType){
    if(offerType == 1){
      this.formControl.controls.offerType.setValue(this.valueRadio.offerType1);
      return;
    }
    if(offerType == 2){
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType2);
      return;
    }
    if(offerType == 3){
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType3);
      return;
    }
    if(offerType == 4){
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType4);
      return;
    }
  }

}
