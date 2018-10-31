import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

import { AjaxService, AuthService, DialogService, MessageBarService } from 'services/index';
import { formatter, TextDateTH, stringToDate } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
import { Int06101, Person } from './int06-10-1.model';
import { Router } from '@angular/router';

declare var $: any;

const ALERT_MSG = {
  refnum: "กรุณากรอกเลขที่เอกสารอ้างอิง",
  withdrawal: "กรุณากรอกวันที่ขอเบิก",
  activity: "กรุณากรอกกิจกรรม",
  budged: "กรุณากรอกงบ",
  category: "กรุณากรอกหมวดย่อย",
  budget: "กรุณากรอกประเภทงบประมาณ",
  amountOfMoney: "กรุณากรอกจำนวนเงินขอเบิก",
  persons: {
    pmmethod: "กรุณากรอกวิธีการจ่าย",
    datePersons: "กรุณากรอกวันที่สั่งจ่าย",
    amountPaid: "กรุณากรอกจำนวนเงินที่จ่าย",
    payee: "กรุณากรอกผู้รับเงิน",
  }
}

@Component({
  selector: 'app-int06-10-1',
  templateUrl: './int06-10-1.component.html',
  styleUrls: ['./int06-10-1.component.css']
})
export class Int06101Component implements OnInit {

  id: any;
  breadcrumb: BreadCrumb[];

  // Dropdown Arrays
  list: any[] = [];
  budged: any[] = [];
  category: any[] = [];
  bankList: any[] = [];
  titleList: any[] = [];
  budgetList: any[] = [];
  pmmethodList: any[] = [];
  activityList: any[] = [];

  // Forms
  formGroup: FormGroup = new FormGroup({});
  persons: FormArray = new FormArray([]);

  // State
  private submitted: boolean = false;
  private unsave: boolean = true;

  constructor(
    private messageBarService: MessageBarService,
    private authService: AuthService,
    private ajax: AjaxService,
    private formBuilder: FormBuilder,
    private dialog: DialogService,
    private router: Router
  ) {

    // Initial Breadcrumb
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกรายการขอเบิก", route: "#" },
    ];

    // Initial FormGroup
    this.formGroup = this.formBuilder.group({
      note: ['', [Validators.maxLength(7)]],
      deductSocial: ['', [Validators.maxLength(7)]],
      withholding: ['', [Validators.maxLength(7)]],
      other: ['', [Validators.maxLength(7)]],
      amountOfMoney1: ['', [Validators.maxLength(7)]],
      numberRequested: ['', [Validators.maxLength(100)]],
      documentNumber: ['', [Validators.maxLength(100)]],
      itemDescription: ['', [Validators.maxLength(1000)]],
      list: ['', [Validators.required, Validators.maxLength(7)]],
      refnum: ['', [Validators.required, Validators.maxLength(100)]],
      withdrawal: ['', [Validators.required, Validators.maxLength(10)]],
      activity: ['', [Validators.required, Validators.maxLength(500)]],
      budged: ['', [Validators.required, Validators.maxLength(20)]],
      category: ['', [Validators.required, Validators.maxLength(100)]],
      budget: ['', [Validators.required, Validators.maxLength(200)]],
      amountOfMoney: ['', [Validators.required, Validators.maxLength(7)]],
      persons: this.formBuilder.array([])
    });

    // Add Person Form to `persons` FormArray
    this.addPerson();

    // Before Leave this page will ask
    window.addEventListener("beforeunload", (e) => {
      const confirmationMessage = "\o/";
      if (this.unsave) {
        (e || window.event).returnValue = confirmationMessage;
        return confirmationMessage;
      }
    });
  }

  ngOnInit() {

    // Initial Calendar
    $("#dateWithdraw").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {
        this.formGroup.get('withdrawal').patchValue(text);
      }
    });
    // Initial Dropdowns
    $(".ui.dropdown.ai").dropdown().css('width', '100%');

    // Dropdowns
    this.callAllDropdown();

    // Page Version
    this.authService.reRenderVersionProgram('INT-06101');

  }

  // Before Leave this page will ask
  canDeactivate(): Observable<boolean> | boolean {
    if (this.unsave) {
      let confirm: any = this.dialog.confirm("ต้องการออกจากที่นี่หรือไม่?");
      if (confirm.value) {
        console.log("Exited...");
      }
      return confirm;
    }
    return true;
  }

  // Initial Form `Person`
  createPerson(): FormGroup {
    const data = {
      amount: ['', [Validators.required, Validators.maxLength(7)]],
      title: ['', [Validators.required, Validators.maxLength(40)]],
      payeeFirst: ['', [Validators.required, Validators.maxLength(80)]],
      payeeLast: ['', [Validators.required, Validators.maxLength(80)]],
      paymentDate: ['', [Validators.required, Validators.maxLength(10)]],
      paymentMethod: ['', [Validators.required, Validators.maxLength(200)]],
      refPayment: ['', [Validators.required, Validators.maxLength(50)]],
      bankName: ['', [Validators.maxLength(200)]]
    }
    return this.formBuilder.group(data);
  }

  // Add Form to FormArray
  addPerson(): void {
    const index = this.persons.length;
    this.persons = this.formGroup.get('persons') as FormArray;
    this.persons.push(this.createPerson());
    setTimeout(() => {
      $(".ui.dropdown.ai").dropdown().css('width', '100%');
      $("#datePersons" + index).calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter(),
        onChange: (date, text) => {
          this.persons.at(index).get('paymentDate').patchValue(text);
        }
      });
    }, 50);
  }

  // Remove Form from FormArray
  delPerson(index: number): void {
    this.persons = this.formGroup.get('persons') as FormArray;
    this.persons.removeAt(index);
  }

  // Submit Form
  saveData() {

    // Is submitted
    this.submitted = true;

    // Check Validators 1st and Alert MESSAGE from constant `ALERT_MSG`
    for (let j in ALERT_MSG) {
      for (let i in this.formGroup.controls) {
        if (i == j) {
          if (i == "persons" && this.persons.invalid) {
            for (let h in ALERT_MSG.persons) {
              for (let g in this.persons) {
                if (h == g && this.persons.get(g).invalid) {
                  this.messageBarService.alert(ALERT_MSG[j], "แจ้งเตือน");
                  return;
                }
              }
            }
          } else if (this.formGroup.get(i).invalid) {
            this.messageBarService.alert(ALERT_MSG[j], "แจ้งเตือน");
            return;
          }
        }
      }
    }

    // Check Validators 2nd
    if (this.formGroup.valid) {
      const URL = "ia/int06101/add";
      const { list, note, deductSocial, withholding, // destruct data from `this.formGroup`
        other, amountOfMoney1, numberRequested, documentNumber,
        itemDescription, refnum, withdrawal, activity,
        budged, category, budget, amountOfMoney, persons } = this.formGroup.value;
      let _persons: Person[] = []; // Person Array
      for (let key in persons) {
        const { amount, paymentMethod, refPayment, paymentDate,
          title, payeeFirst, payeeLast, bankName } = persons[key];
        _persons.push({
          amount: parseFloat(amount),
          paymentMethod: paymentMethod,
          refPayment: refPayment,
          paymentDate: stringToDate(paymentDate),
          payee: `${title}${payeeFirst} ${payeeLast}`,
          bankName: bankName,
        })
      }
      const data: Int06101 = { // Binding Data
        refnum: refnum, withdrawal: withdrawal, activity: activity,
        budged: budged, budget: budget, category: category,
        list: list, amountOfMoney: amountOfMoney, deductSocial: deductSocial,
        withholding: withholding, other: other, amountOfMoney1: amountOfMoney1,
        numberRequested: numberRequested, documentNumber: documentNumber,
        itemDescription: itemDescription, note: note,
        budgetName: this.budged.find(obj => obj.budgetId == budged).budgetName,
        listName: this.list.find(obj => obj.listId == list).listName,
        categoryName: this.category.find(obj => obj.categoryId == category).categoryName,
        persons: _persons
      };
      this.ajax.post(URL, data, res => {
        const msg = res.json();
        if (msg.messageType == "C") {
          this.messageBarService.successModal(msg.messageTh);
          this.unsave = false;
          this.router.navigate(['/int06/10']);
        } else {
          this.messageBarService.errorModal(msg.messageTh);
        }
      });
    }
  }


  // Call All Ajax Dropdown
  callAllDropdown = () => {
    this.budgeDropdown();
    this.pmmethod();
    this.activity();
    this.budge();
    this.title();
    this.bank();
  }

  // Ajax Dropdown
  pmmethod = () => {
    let url = "ia/int06101/pmmethod"
    this.ajax.post(url, {}, res => {
      this.pmmethodList = res.json();
    })
  }

  // Ajax Dropdown
  activity = () => {
    let url = "ia/int06101/activity"
    this.ajax.post(url, {}, res => {
      this.activityList = res.json();
    })
  }

  // Ajax Dropdown
  budge = () => {
    let url = "ia/int06101/budge"
    this.ajax.post(url, {}, res => {
      this.budgetList = res.json();
    })
  }

  // Ajax Dropdown
  title = () => {
    let url = "ia/int06101/title"
    this.ajax.post(url, {}, res => {
      this.titleList = res.json();
    })
  }

  // Ajax Dropdown
  bank = () => {
    let url = "ia/int06101/bank"
    this.ajax.post(url, {}, res => {
      this.bankList = res.json();
    })
  }

  // Ajax Dropdown
  budgeDropdown = () => {
    const URL = "ia/int06101/budged";
    this.ajax.post(URL, {}, res => {
      this.budged = res.json();
    });
  }

  // Ajax Dropdown
  budgedOnchange = (e) => {
    $("#category").dropdown('restore defaults');
    const URL = "ia/int06101/category";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, { budgetId: params }, res => {
        this.category = res.json();
      });
    }
  }

  // Ajax Dropdown
  categoryOnchange = (e) => {
    $("#list").dropdown('restore defaults');
    const URL = "ia/int06101/list";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, { categoryId: params }, res => {
        this.list = res.json();
      });
    }
  }

  // State Checking
  invalidFormGroup(control) { return this.submitted && this.formGroup.get(control).invalid }
  invalidFormArray(index, control) { return this.submitted && this.persons.at(index).get(control).invalid }

}
