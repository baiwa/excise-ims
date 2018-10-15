import { Component, OnInit, ViewChild } from "@angular/core";
import { Int0691Service } from "projects/internal-audit/int06/int06-9/int06-9-1/int06-9-1.service";
import { BreadCrumb } from "models/breadcrumb";
import { NgForm, FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { TextDateTH, formatter } from "helpers/datepicker";
import { ActivatedRoute } from "@angular/router";
import { async } from "@angular/core/testing";

declare var $: any;
@Component({
  selector: "app-int06-9-1",
  templateUrl: "./int06-9-1.component.html",
  styleUrls: ["./int06-9-1.component.css"],
  providers: [Int0691Service]
})
export class Int0691Component implements OnInit {
  // @ViewChild("f")
  // form: NgForm; // #f
  transferForm: FormGroup;

  breadcrumb: BreadCrumb[] = [];
  comboBox1: Observable<ComboBox[]>;
  comboBox2: Observable<ComboBox[]>;
  comboBox3: Promise<any>;
  combobox4: any = [];
  combo4: boolean = false;
  combobox5: Promise<any>;
  comboBox6: Observable<ComboBox[]>;
  transferId: any = "";
  flag: any = "";
  submitted = false;
  budgetData: any = [];
  // loading = true;

  constructor(
    private formBuilder: FormBuilder,
    private selfService: Int0691Service,
    private route: ActivatedRoute
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกข้อมูลรับโอนเงิน", route: "#" }
    ];
  }

  ngAfterViewInit() {
    $("#int0621").hide();
    $(".ui.dropdown.ai")
      .dropdown()
      .css("width", "100%");
  }

  async ngOnInit() {
    //set formbuilder
    this.transferForm = this.formBuilder.group({
      mofNum: ["", Validators.required],
      refNum: ["", Validators.required],
      refDate: ["", Validators.required],
      transferList: ["", Validators.required],
      budgetType: ["", Validators.required],
      budget: ["", Validators.required],
      ctgBudget: ["", Validators.required],
      subCtgBudget: ["", Validators.required],
      activities: ["", Validators.required],
      descriptionList: ["", Validators.required],
      amount: ["", Validators.required],
      note: ["", Validators.required]
    });

    //call combobox
    this.combobox();
    //on flag 'EDIT' Int06-9
    this.transferId = this.route.snapshot.queryParams["transferId"];
    this.flag = this.route.snapshot.queryParams["flag"];

    $(".ui.dropdown.ai")
      .dropdown()
      .css("width", "100%");
    $("#calendar").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (a, b, c) => {
        this.transferForm.patchValue({ refDate: b });
      }
    });
  }

  onFlagEDIT() {
    this.selfService.queryByIdToEdit(this.transferId).then(data => {
      let dataEdit = data;
      this.setValueOnEdit(dataEdit);
    });
  }

  async combobox() {
    //ALL TABLE BUDGETLIST //query to compare index
    this.selfService.queryBudgetData().subscribe(data => {
      data.forEach(obj => {
        this.budgetData.push(obj);
      });
    });

    let callItemBox1 = new Promise((resolve, reject) => {
      this.comboBox1 = this.selfService.dropdown("TRANSFER", null, resolve);
    });

    let callItemBox2 = new Promise((resolve, reject) => {
      this.comboBox2 = this.selfService.dropdown("BUDGET_TYPE", null, resolve);
    });

    let callItemBox3 = new Promise((resolve, reject) => {
      this.comboBox6 = this.selfService.dropdown("ACTIVITY", null, resolve);
    });

    Promise.all([callItemBox1, callItemBox2, callItemBox3]).then(async () => {
      this.comboBox3 = await this.selfService.quryBudgetName();
      if (this.flag === "EDIT") {
        await this.onFlagEDIT();
      }
    });
  }

  get f() {
    return this.transferForm.controls;
  }

  addData() {
    this.submitted = true;
    if (this.flag === "SAVE") {
      // stop here if form is invalid
      if (this.transferForm.invalid) {
        return;
      } else {
        console.log("valid");
        //form is valid
        this.selfService.addData(this.transferForm.value, this.flag);
      }
    } else {
      if (this.transferForm.invalid) {
        return;
      }
      this.selfService.addData(
        this.transferForm.value,
        this.flag,
        this.transferId
      );
    }
  }

  checkStatus(status: string) {
    console.log(status);
    this.flag = status;
  }

  setValueOnEdit(data) {
    console.log(this.budgetData);
    console.log("data: ", data);
    let dataFilter = this.budgetData.filter(
      obj => obj.listId == data.budgetCode
    );

    console.log(dataFilter);
    //filter combobox3
    let filterIdCombo3 = dataFilter[0].budgetId;
    //filter combobox4
    let filterIdCombo4 = dataFilter[0].categoryId;
    //filter combobox5
    let filterIdCombo5 = dataFilter[0].listId;
    console.log(filterIdCombo3, " ", filterIdCombo4, " ", filterIdCombo5);

    this.transferForm.patchValue({
      mofNum: data.mofNum,
      refNum: data.refNum,
      refDate: data.refDateStr,
      // transferList: data.transferList,
      // budgetType: data.budgetType,
      // budget: filterIdCombo3,
      // ctgBudget: filterIdCombo4,
      // subCtgBudget: filterIdCombo5,
      // activities: data.activities,
      descriptionList: data.descriptionList,
      amount: data.amount,
      note: data.note
    });

    let promise = new Promise((resolve, reject) => {
      // this.transferForm.patchValue({ budget: filterIdCombo3 });
      $("#transferList").dropdown("set selected", data.transferList);
      $("#budgetType").dropdown("set selected", data.budgetType);
      $("#activities").dropdown("set selected", data.activities);
      $("#budget").dropdown("set selected", filterIdCombo3);
      setTimeout(() => resolve(true), 500);
    });

    promise
      .then(resolve => {
        if (resolve) {
          $("#ctgBudget").dropdown("set selected", filterIdCombo4);
        }
      })
      .then(() => {
        setTimeout(() => {
          $("#subCtgBudget").dropdown("set selected", filterIdCombo5);
        }, 500);
      });
  }

  hidedata() {
    this.selfService.clearData();
  }

  onSave() {
    this.selfService.saveDatatable();
  }

  async budgetChange(e) {
    await this.selfService
      .getCtgBudget(e.target.value)
      .then(value => {
        value.forEach(obj => {
          this.combobox4.push(obj);
        });
      })
      .then(() => {
        this.combo4 = true;
        console.log(this.combo4);
      });
  }

  async ctgBudgetChange(e) {
    await this.selfService.getListName(e.target.value).then(value => {
      this.combobox5 = value;
    });
  }
}
