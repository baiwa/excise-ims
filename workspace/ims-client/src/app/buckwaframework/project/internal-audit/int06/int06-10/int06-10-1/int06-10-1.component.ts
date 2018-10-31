import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { formatter, TextDateTH, stringToDate } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { IaFollowUpProject } from 'models/IaFollowUpProject';
import { ActivatedRoute } from '@angular/router';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Int06101, Person } from './int06-10-1.model';


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

declare var $: any;
@Component({
  selector: 'app-int06-10-1',
  templateUrl: './int06-10-1.component.html',
  styleUrls: ['./int06-10-1.component.css']
})
export class Int06101Component implements OnInit {
  num1: number[];
  num2: number[];
  iaFollowUpProject: IaFollowUpProject;
  $form: any;
  id: any;
  percent1: string[];
  percent2: string[];
  percent3: string[];
  breadcrumb: BreadCrumb[]
  pmmethodList: any;
  activityList: any;
  budgetList: any;
  budged: any;
  listButton: any;
  numberButton: number;
  numbers: number[];
  category: any;
  list: any;
  travelToDescription: any;
  titleList: any;
  bankList: any;

  formGroup: FormGroup = new FormGroup({});
  persons: FormArray = new FormArray([]);

  constructor(
    private messageBarService: MessageBarService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private formBuilder: FormBuilder
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกรายการขอเบิก", route: "#" },

    ];
    this.formGroup = this.formBuilder.group({
      list: [''],
      note: [''],
      deductSocial: [''],
      withholding: [''],
      other: [''],
      amountOfMoney1: [''],
      numberRequested: [''],
      documentNumber: [''],
      itemDescription: [''],
      refnum: ['', Validators.required],
      withdrawal: ['', Validators.required],
      activity: ['', Validators.required],
      budged: ['', Validators.required],
      category: ['', Validators.required],
      budget: ['', Validators.required],
      amountOfMoney: ['', Validators.required],
      persons: this.formBuilder.array([])
    });
    this.addPerson();
  }

  ngOnInit() {
    $("#dateWithdraw").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {
        this.formGroup.get('withdrawal').patchValue(text);
      }
    });
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
    this.authService.reRenderVersionProgram('INT-06101');
    this.budgeDropdown();
    this.pmmethod();
    this.activity();
    this.budge();
    this.title();
    this.bank();
  }

  createPerson(): FormGroup {
    const data = {
      amount: ['', Validators.required],
      title: ['', Validators.required],
      payeeFirst: ['', Validators.required],
      payeeLast: ['', Validators.required],
      paymentDate: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      refPayment: ['', Validators.required],
      bankName: ['']
    }
    return this.formBuilder.group(data);
  }

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

  delPerson(index: number): void {
    this.persons = this.formGroup.get('persons') as FormArray;
    this.persons.removeAt(index);
  }

  saveData() {

    for (let j in ALERT_MSG) {
      for (let i in this.formGroup.controls) {
        // console.log(i, j);
        // if (i == "persons") {
        //   console.log(this.persons.value);
        // }
        if (j == i && this.formGroup.get(i).invalid) {
          this.messageBarService.alert(ALERT_MSG[j], "แจ้งเตือน");
          return;
        }
      }
    }

    if (this.formGroup.valid) {
      const URL = "ia/int06101/add";
      const { list, note, deductSocial, withholding,
        other, amountOfMoney1, numberRequested, documentNumber,
        itemDescription, refnum, withdrawal, activity,
        budged, category, budget, amountOfMoney, persons } = this.formGroup.value;
      let _persons: Person[] = [];
      for(let key in persons) {
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
      const data: Int06101 = {
        refnum: refnum,
        withdrawal: withdrawal,
        activity: activity,
        budged: budged,
        budget: budget,
        category: category,
        list: list,
        amountOfMoney: amountOfMoney,
        deductSocial: deductSocial,
        withholding: withholding,
        other: other,
        amountOfMoney1: amountOfMoney1,
        numberRequested: numberRequested,
        documentNumber: documentNumber,
        itemDescription: itemDescription,
        note: note,
        budgetName: this.budged.find(obj => obj.budgetId == budged).budgetName,
        listName: this.list.find(obj => obj.listId == list).listName,
        categoryName: this.category.find(obj => obj.categoryId == category).categoryName,
        persons: _persons
      };
      this.ajax.post(URL, data, res => {
        const msg = res.json();

        if (msg.messageType == "C") {
          this.messageBarService.successModal(msg.messageTh);
        } else {
          this.messageBarService.errorModal(msg.messageTh);
        }
        $("#searchFlag").val("TRUE");
        $('#tableData').DataTable().ajax.reload();
      });
    }
  }

  pmmethod = () => {
    let url = "ia/int06101/pmmethod"
    this.ajax.post(url, {}, res => {
      this.pmmethodList = res.json();
    })
  }

  activity = () => {
    let url = "ia/int06101/activity"
    this.ajax.post(url, {}, res => {
      this.activityList = res.json();
    })
  }

  budge = () => {
    let url = "ia/int06101/budge"
    this.ajax.post(url, {}, res => {
      this.budgetList = res.json();
    })
  }

  title = () => {
    let url = "ia/int06101/title"
    this.ajax.post(url, {}, res => {
      this.titleList = res.json();
    })
  }

  bank = () => {
    let url = "ia/int06101/bank"
    this.ajax.post(url, {}, res => {
      this.bankList = res.json();
    })
  }

  budgeDropdown = () => {
    const URL = "ia/int06101/budged";
    this.ajax.post(URL, {}, res => {
      this.budged = res.json();
      console.log(this.budged);
    });
  }

  budgedOnchange = (e) => {
    $("#category").dropdown('restore defaults');
    const URL = "ia/int06101/category";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, { budgetId: params }, res => {
        console.log("Id : ", res.json());
        this.category = res.json();
      });
    }
  }

  categoryOnchange = (e) => {
    $("#list").dropdown('restore defaults');
    const URL = "ia/int06101/list";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, { categoryId: params }, res => {
        console.log("Id : ", res.json());
        this.list = res.json();
      });
    }
  }

}
