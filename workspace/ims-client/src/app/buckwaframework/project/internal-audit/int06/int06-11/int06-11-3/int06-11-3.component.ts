import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { FormGroup, Validators, FormBuilder, FormArray } from '@angular/forms';
import { Int06113Service } from './int06-11-3.service';
import { Router } from '@angular/router';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { IaService } from 'services/ia.service';
import { Utils } from 'helpers/utils';
declare var $: any;
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
    { label: "บันทึกคำขอ", route: "#" },
    { label: "ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร", route: "#" },
  ];

  // ===> params
  formControl: FormGroup;
  items: FormArray;
  loading: boolean = false;
  submitted: boolean = false;

  valueRadio: any = {
    mateDescription1: "ไม่เป็นข้าราชการประจำหรือลูกจ้างประจำ",
    mateDescription2: "เป็นข้าราชการ",
    mateDescription3: "ลูกจ้างประจำ",
    mateDescription4: "เป็นพนักงานหรือลูกจ้างใน รัฐวิสาหกิจ/หน่วยงานของทางราชการ ราชการส่วนท้องถิ่น กรุงเทพมหานคร องค์กรอิสระ องค์กรมหาชน หรือหน่วยงานอื่นใด",

    status1: "เป็นบิดาชอบด้วยกฎหมาย",
    statsu2: "เป็นมารดา",

    type1: "เงินบำรุงการศึกษา",
    type2: "เงินค่าเล่าเรียน",

    typeRecive1: "ตามสิทธิ",
    typeRecive2: "เฉพาะส่วนที่ยังขาดจากสิทธิ เป็นเงิน",

    offerType1: "ข้าพเจ้ามีสิทธิได้รับเงินช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตรและข้อความที่ระบุข้างต้นเป็นความจริง",
    offerType2: "บุตรของข้าพเจ้าอยู่ในข่ายได้รับการช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตร",
    offerType3: "เป็นผู้ใช้สิทธิเบิกเงินช่วยเหลือตามพระราชกฤษฎีกาเงินสวัสดิการเกี่ยวกับการศึกษาของบุตร แต่เพียงฝ่ายเดียว",
    offerType4: "คู่สมรสของข้าพเจ้าได้รับการช่วยเหลือจากรัฐวิสาหกิจ หน่วยงานของทางราชการ ราชการท้องถิ่นกรุงเทพมหานครองค์กรอิสระ องค์การมหาชน หรือหน่วยงานอื่นใด ต่า กว่าจา นวนที่ได้รับจากทางราชการ"
  }
  userDetails: any;
  money: any;
  countFrom: number = 0;
  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private myService: Int06113Service,
    private router: Router,
    private iaService: IaService
  ) {
    this.newFormControl();
    this.items = this.formControl.get('items') as FormArray;
    // this.items.push(this.createChlidItem());
    // this.items.push(this.createChlidItem());

    this.money = this.iaService.getData();
    if (Utils.isNull(this.money)) { this.router.navigate(['/int06/11/3-1']) }
  }

  ngOnInit() {
    console.log("money : ", this.money);
    this.checkMoney(this.money);
    this.authService.reRenderVersionProgram('INT-06113').then(res => {
      this.userDetails = res;
      this.formControl.controls.name.setValue(this.userDetails.fullName);
      this.formControl.controls.pition.setValue(this.userDetails.title);
      this.formControl.controls.belong.setValue(this.userDetails.position);
    });

    // this.items.push(this.createChlidItem());
  }
  ngAfterViewInit() {
    this.calenda();
  }
  checkMoney(money) {
    this.countFrom = Utils.isNotNull(money.money1) ? this.countFrom+=1 : this.countFrom+=0;
    this.countFrom = Utils.isNotNull(money.money2) ? this.countFrom+=1 : this.countFrom+=0;
    this.countFrom = Utils.isNotNull(money.money3) ? this.countFrom+=1 : this.countFrom+=0;
    console.log("countFrom : ", this.countFrom);

    for(let i=0;i<this.countFrom;i++){
      this.items.push(this.createChlidItem());   
    }
  }
  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      //==> 1
      name: ["", Validators.required],
      pition: ["", Validators.required],
      belong: ["", Validators.required],
      //==> 2
      mate: [""],
      mateDescription: ["1"],
      pitionMate: [""],
      belongMate: [""],
      //==> 3
      status: ["1"],
      //==> 4
      type: ["1"],
      typeRecive: ["1"],
      typeReciveAmount: ["", Validators.required],
      offer: ["", Validators.required],
      offerType: ["1"],
      sumAmount: ["", Validators.required],

      items: this.formBuilder.array([])
    });
  }
  createChlidItem(): FormGroup {
    return this.formBuilder.group({
      name: ["", Validators.required],
      birth: [""],
      orderFather: ["", Validators.required],
      orderMather: ["", Validators.required],
      orderReplace: '',
      nameReplace: '',
      birthReplace: '',
      dateDeadReplace: '',
      education: ["", Validators.required],
      educationProvince: ["", Validators.required],
      educationDistrict: ["", Validators.required],
      educationClass: ["", Validators.required],
      amount: ["", Validators.required]
    });
  }
  get f() {
    return this.formControl.controls;
  }

  formArrayValid(index, control) {
    return this.submitted && this.items.at(index).get(control).invalid;
  }



  save(event: any) {
    event.preventDefault();
    let birth = $(".birth input");
    let birthReplace = $(".birthReplace input");
    let dateDeadReplace = $(".dateDeadReplace input");

    for (let i = 0; i < birth.length; i++) {

      let _birth = $(birth[i]).val();
      this.items.value[i].birth = _birth;

      let _birthReplace = $(birthReplace[i]).val();
      this.items.value[i].birthReplace = _birthReplace;

      let _dateDeadReplace = $(dateDeadReplace[i]).val();
      this.items.value[i].dateDeadReplace = _dateDeadReplace;
      console.log("item : ", this.items);
      console.log("item controls : ", this.items.controls[i]);
      console.log(_birth);
      console.log("item value:", this.items.controls[i].patchValue({
        birth: _birth
      }));
      console.log("value : ", this.items.controls[i].value);
      console.log("---------------------------------------------------")
    }

    event.preventDefault();
    this.submitted = true;
    if (this.formControl.invalid) {
      return;
    }
    // this.loading = true;
    // this.checkMateDescription(this.formControl.controls.mateDescription.value);
    // this.checkStatus(this.formControl.controls.status.value);
    // this.checkType(this.formControl.controls.type.value);
    // this.checkTypeRecive(this.formControl.controls.typeRecive.value);
    // this.checkOfferType(this.formControl.controls.offerType.value);

    // this.dateChange();

    // this.myService.save(this.formControl.value).then(res=>{      
    //   this.loading = false;
    //   this.router.navigate(['/int06/11']);
    // }).catch(rej=>{
    //   this.router.navigate(['/int06/11']);
    // });
  }

  checkMateDescription(checkMateDescription) {
    if (checkMateDescription == 1) {
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription1);
      return;
    }
    if (checkMateDescription == 2) {
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription2);
      return;
    }
    if (checkMateDescription == 3) {
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription3);
      return;
    }
    if (checkMateDescription == 4) {
      this.formControl.controls.mateDescription.setValue(this.valueRadio.mateDescription4);
      return;
    }
  }
  checkStatus(statsu) {
    if (statsu == 1) {
      this.formControl.controls.status.setValue(this.valueRadio.status1);
      return;
    }
    if (statsu == 2) {
      this.formControl.controls.status.setValue(this.valueRadio.status2);
      return;
    }
  }

  checkType(type) {
    if (type == 1) {
      this.formControl.controls.type.setValue(this.valueRadio.type1);
      return;
    }
    if (type == 2) {
      this.formControl.controls.type.setValue(this.valueRadio.type2);
      return;
    }
  }
  checkTypeRecive(typeRecive) {
    if (typeRecive == 1) {
      this.formControl.controls.typeRecive.setValue(this.valueRadio.typeRecive1);
      return;
    }
    if (typeRecive == 2) {
      this.formControl.controls.typeRecive.setValue(this.valueRadio.typeRecive2);
      return;
    }
  }

  checkOfferType(offerType) {
    if (offerType == 1) {
      this.formControl.controls.offerType.setValue(this.valueRadio.offerType1);
      return;
    }
    if (offerType == 2) {
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType2);
      return;
    }
    if (offerType == 3) {
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType3);
      return;
    }
    if (offerType == 4) {
      this.formControl.controls.typeRecive.setValue(this.valueRadio.offerType4);
      return;
    }
  }

  calenda = () => {
    $(".birth").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {
        //  this.dateChange();
      }
    });

    $(".birthReplace").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {
        // this.dateChange();
      }
    });

    $(".dateDeadReplace").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {
        //this.dateChange();
      }
    });
  }

  // dateChange=()=>{
  //   let birth = $(".birth input"); 
  //   let birthReplace = $(".birthReplace input");
  //   let dateDeadReplace = $(".dateDeadReplace input");
  //   for(let i =0; i<birth.length; i++){

  //     let _birth = $(birth[i]).val();
  //     console.log("_birth : ",_birth);
  //     let d1= this.items.value[i].birth = (_birth) ;

  //     let _birthReplace = $(birthReplace[i]).val();
  //     let d2= this.items.value[i].birthReplace = _birthReplace;

  //     let _dateDeadReplace = $(dateDeadReplace[i]).val();
  //     let d3= this.items.value[i].dateDeadReplace = _dateDeadReplace;

  //     console.log("date :", _birth);
  //     console.log("this.items.value[i].birth : ",this.items.value[i].birth);
  //   }
  // }
}
