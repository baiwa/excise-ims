import { Component, OnInit, AfterViewInit } from "@angular/core";
import { FormGroup, FormControl, Validators, NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { Int0611Service, Lov } from "./int06-11.service";
import { AuthService } from "services/auth.service";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";

declare var $: any;

@Component({
  selector: "app-int06-11",
  templateUrl: "./int06-11.component.html",
  styleUrls: ["./int06-11.component.css"],
  providers: [Int0611Service]
})
export class Int0611Component implements OnInit, AfterViewInit {
  form: FormGroup = new FormGroup({
    bill: new FormControl("", Validators.required),
    type: new FormControl("", Validators.required),
    level: new FormControl("", Validators.required),
    salary: new FormControl("", Validators.required),

    /**
     * Child FormControls
     */
    // Checkboxes
    chkChild0: new FormControl(false, Validators.required),
    chkChild1: new FormControl(false, Validators.required),
    chkChild2: new FormControl(false, Validators.required),
    // Type Radios
    typeChild0: new FormControl({ disabled: false }),
    typeChild1: new FormControl({ disabled: false }),
    typeChild2: new FormControl({ disabled: false }),
    // Level Dropdowns
    levelChild0: new FormControl({ disabled: false }),
    levelChild1: new FormControl({ disabled: false }),
    levelChild2: new FormControl({ disabled: false }),
    // Major Dropdowns
    majorChild0: new FormControl({ disabled: false }),
    majorChild1: new FormControl({ disabled: false }),
    majorChild2: new FormControl({ disabled: false }),
    // TypeEducation Dropdowns
    typeEduChild0: new FormControl({ disabled: false }),
    typeEduChild1: new FormControl({ disabled: false }),
    typeEduChild2: new FormControl({ disabled: false })
  });

  bills: Lov[] = [];
  // types: Lov[] = [];
  // levels: Lov[] = [];
  levelChilds: Lov[] = [];
  majorChilds: Lov[] = [];
  typeEduChilds: Lov[] = [];
  children: any[] = [1, 2, 3];
  types: Observable<ComboBox>;
  // levels: Observable<ComboBox>;
  // salarys: Observable<ComboBox>;
  constructor(
    private router: Router,
    private service: Int0611Service,
    private authService: AuthService
  ) {
    this.initDropdowns();
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-06110");
  }

  ngAfterViewInit() {
    $(`.ui.fluid.dropdown`)
      .dropdown({
        clearable: true
      })
      .css("width", "100%");
  }

  comboTypeChange(e, list: string) {
    e.preventDefault();
    console.log(e.target.value);
    this[list] = this.service.dropdown("WITHDRAW_TYPE", e.target.value);
  }

  /**
   * Handles
   */
  handleSubmit(e: any) {
    e.preventDefault(); // prevent refresh to post form
    for (let key in this.form.controls) {
      if (this.form.controls[key].invalid) {
        console.log(key);
      }
    }
    if (this.form.valid) {
      this.changepage();
    }
  }
  handleBill(control) {
    switch (control.target.value) {
      case "1":
        this.setControls("type"); // Type Set Validators
        this.setControls("level"); // Level Set Validators
        for (let i = 0; i < 3; i++) {
          this.clearControls(`chkChild${i}`);
        }
        break;
      case "2":
        this.clearControls("type"); // Type Clear Validators
        this.clearControls("level"); // Level Clear Validators
        for (let i = 0; i < 3; i++) {
          this.setControls(`chkChild${i}`);
        }
        break;
      case "3":
        this.clearControls("type"); // Type Clear Validators
        this.clearControls("level"); // Level Clear Validators
        for (let i = 0; i < 3; i++) {
          this.setControls(`chkChild${i}`);
        }
        break;
      case "4":
        this.setControls("type"); // Type Set Validators
        this.setControls("level"); // Level Set Validators
        for (let i = 0; i < 3; i++) {
          this.clearControls(`chkChild${i}`);
        }
        break;
      default:
        this.setControls("type"); // Type Set Validators
        this.setControls("level"); // Level Set Validators
        for (let i = 0; i < 3; i++) {
          this.setControls(`chkChild${i}`);
        }
        break;
    }
  }
  handleChkChild(control, i) {
    if (control.target.checked) {
      $(`#levelChild${i}`)
        .parent()
        .removeClass("disabled");
      $(`#majorChild${i}`)
        .parent()
        .removeClass("disabled");
      $(`#typeEduChild${i}`)
        .parent()
        .removeClass("disabled");
      this.setControls(`typeChild${i}`);
      this.setControls(`levelChild${i}`);
      this.setControls(`majorChild${i}`);
      this.setControls(`typeEduChild${i}`);
      for (let o = 0; o < 3; o++) {
        if (i != o) {
          this.clearControls(`chkChild${o}`);
        }
      }
    } else {
      $(`#levelChild${i}`).dropdown("restore defaults");
      $(`#majorChild${i}`).dropdown("restore defaults");
      $(`#typeEduChild${i}`).dropdown("restore defaults");
      $(`#majorChild${i}`)
        .parent()
        .addClass("disabled");
      $(`#typeEduChild${i}`)
        .parent()
        .addClass("disabled");
      $(`#levelChild${i}`)
        .parent()
        .addClass("disabled");
      $(`#majorChild${i}`)
        .parent()
        .addClass("disabled");
      $(`#typeEduChild${i}`)
        .parent()
        .addClass("disabled");
      this.clearControls(`typeChild${i}`);
      this.clearControls(`levelChild${i}`);
      this.clearControls(`majorChild${i}`);
      this.clearControls(`typeEduChild${i}`);
    }
  }
  /**
   * Initial functions
   */
  async initDropdowns() {
    // Loading Dropdowns
    this.types = this.service.dropdown("WITHDRAW_TYPE", null);
    /* --------------------------------- */
    this.bills = await this.service.getBills();
    // this.types = await this.service.getTypes();
    // this.levels = await this.service.getLevels();
    this.levelChilds = await this.service.getLevelChilds();
    this.majorChilds = await this.service.getMajorChilds();
    this.typeEduChilds = await this.service.getTypeEduChilds();
  }

  /**
   * Class by actions
   */
  disabled(control: string) {
    const controls = this.form.controls;
    const classes = {
      disabled: controls[control].value
    };
    return classes;
  }
  required(control: string) {
    const controls = this.form.controls;
    const classes = {
      required: controls[control].errors && controls[control].errors.required
    };
    return classes;
  }
  hidden(arr: any[]) {
    const bill = this.form.controls.bill;
    const classes = {
      hidden: this.canHide(arr, bill.value)
    };
    return classes;
  }
  invalid(control: string, ngForm: NgForm) {
    const controls = this.form.controls;
    const classes = {
      error:
        (controls[control].dirty ||
          controls[control].touched ||
          ngForm.submitted) &&
        controls[control].invalid
    };
    return classes;
  }
  canHide(arr: any[], value: any) {
    let hidden = false;
    if (value == "") {
      hidden = true;
    }
    for (let i = 0; i < arr.length; i++) {
      if (value == arr[i]) {
        hidden = true;
      }
    }
    return hidden;
  }
  setControls(control: string) {
    this.form.controls[control].setValidators([Validators.required]);
    this.form.controls[control].enable();
    this.form.controls[control].updateValueAndValidity();
  }
  clearControls(control: string) {
    this.form.controls[control].clearValidators();
    this.form.controls[control].setValue("");
    this.form.controls[control].disable();
    this.form.controls[control].updateValueAndValidity();
  }

  changepage() {
    const bill = this.form.controls.bill;
    let page = "";
    if (bill.value == "1") {
      page = "int06/11/1";
    } else if (bill.value == "2") {
      page = "int06/11/2";
    } else if (bill.value == "3") {
      page = "int06/11/3";
    } else if (bill.value == "4") {
      page = "int06/11/4";
    }

    this.router.navigate([page], { queryParams: { id: "1234" } });
  }
}
