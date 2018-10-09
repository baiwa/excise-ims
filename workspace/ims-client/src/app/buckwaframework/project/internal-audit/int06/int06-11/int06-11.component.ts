import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { Int0611Service, Lov } from './int06-11.service';

declare var $: any;

@Component({
  selector: 'app-int06-11',
  templateUrl: './int06-11.component.html',
  styleUrls: ['./int06-11.component.css'],
  providers: [Int0611Service]
})
export class Int0611Component implements OnInit, AfterViewInit {

  form: FormGroup = new FormGroup({
    bill: new FormControl('3', Validators.required),
    type: new FormControl('', Validators.required),
    level: new FormControl('', Validators.required),

    /**
     * Child FormControls
     */
    // Checkboxes
    chkChild0: new FormControl(false, Validators.required),
    chkChild1: new FormControl(false, Validators.required),
    chkChild2: new FormControl(false, Validators.required),
    // Type Radios
    typeChild0: new FormControl({ disabled: false }, Validators.required),
    typeChild1: new FormControl({ disabled: false }, Validators.required),
    typeChild2: new FormControl({ disabled: false }, Validators.required),
    // Level Dropdowns
    levelChild0: new FormControl({ disabled: false }, Validators.required),
    levelChild1: new FormControl({ disabled: false }, Validators.required),
    levelChild2: new FormControl({ disabled: false }, Validators.required),
    // Major Dropdowns
    majorChild0: new FormControl({ disabled: false }, Validators.required),
    majorChild1: new FormControl({ disabled: false }, Validators.required),
    majorChild2: new FormControl({ disabled: false }, Validators.required),
    // TypeEducation Dropdowns
    typeEduChild0: new FormControl({ disabled: false }, Validators.required),
    typeEduChild1: new FormControl({ disabled: false }, Validators.required),
    typeEduChild2: new FormControl({ disabled: false }, Validators.required),
  });

  bills: Lov[] = [];
  types: Lov[] = [];
  levels: Lov[] = [];
  levelChilds: Lov[] = [];
  majorChilds: Lov[] = [];
  typeEduChilds: Lov[] = [];
  children: any[] = [1, 2, 3];
  constructor(private router: Router, private service: Int0611Service) {
    this.initDropdowns();
  }

  ngOnInit() { }

  ngAfterViewInit() {
    $(`.ui.fluid.dropdown`).dropdown({
      clearable: true
    }).css('width', '100%');
  }

  /**
   * Handles
   */
  handleSubmit(e: any) {
    e.preventDefault(); // prevent refresh to post form
    if (this.form.valid) {
      console.log(this.form.controls);
    }
  }
  handleBill(control) {
    const { type, level } = this.form.controls;
    const controls = this.form.controls;
    switch (control.target.value) {
      case '1':
        type.setValidators([Validators.required]); // Type Set Validators
        level.setValidators([Validators.required]); // Level Set Validators
        for (let i = 0; i < 3; i++) {
          controls[`chkChild${i}`].clearValidators();
          controls[`typeChild${i}`].clearValidators();
        }
        break;
      case '2':
        type.clearValidators(); // Type Clear Validators
        level.clearValidators(); // Level Clear Validators
        break;
      case '3':
        type.clearValidators(); // Type Clear Validators
        level.clearValidators(); // Level Clear Validators
        break;
      case '4':
        type.setValidators([Validators.required]); // Type Set Validators
        level.setValidators([Validators.required]); // Level Set Validators
        for (let i = 0; i < 3; i++) {
          controls[`chkChild${i}`].clearValidators();
          controls[`typeChild${i}`].clearValidators();
        }
        break;
    }
    this.form.updateValueAndValidity();
  }
  handleChkChild(control, i) {
    if (control.target.checked) {
      $(`#levelChild${i}`).parent().removeClass('disabled');
      $(`#majorChild${i}`).parent().removeClass('disabled');
      $(`#typeEduChild${i}`).parent().removeClass('disabled');
      this.setControls(`levelChild${i}`);
      this.setControls(`majorChild${i}`);
      this.setControls(`typeEduChild${i}`);
    } else {
      $(`#levelChild${i}`).dropdown('restore defaults');
      $(`#majorChild${i}`).dropdown('restore defaults');
      $(`#typeEduChild${i}`).dropdown('restore defaults');
      $(`#majorChild${i}`).parent().addClass('disabled');
      $(`#typeEduChild${i}`).parent().addClass('disabled');
      $(`#levelChild${i}`).parent().addClass('disabled');
      $(`#majorChild${i}`).parent().addClass('disabled');
      $(`#typeEduChild${i}`).parent().addClass('disabled');
      this.clearControls(`levelChild${i}`);
      this.clearControls(`majorChild${i}`);
      this.clearControls(`typeEduChild${i}`);
    }
  }
  /**
   * Initial functions
   */
  async initDropdowns() { // Loading Dropdowns
    this.bills = await this.service.getBills();
    this.types = await this.service.getTypes();
    this.levels = await this.service.getLevels();
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
      'disabled': controls[control].value
    }
    return classes;
  }
  required(control: string) {
    const controls = this.form.controls;
    const classes = {
      'required': controls[control].errors && controls[control].errors.required
    }
    return classes;
  }
  hidden(arr: any[]) {
    const bill = this.form.controls.bill;
    const classes = {
      'hidden': this.canHide(arr, bill.value)
    };
    return classes;
  }
  invalid(control: string, ngForm: NgForm) {
    const controls = this.form.controls;
    const classes = {
      'error': (controls[control].dirty || controls[control].touched || ngForm.submitted) && controls[control].invalid
    };
    return classes;
  }
  canHide(arr: any[], value: any) {
    let hidden = false;
    if (value == '') {
      hidden = true;
    }
    for (let i = 0; i < arr.length; i++) {
      if (value == arr[i]) {
        hidden = true;
      }
    }
    return hidden;
  }
  setControls(control: string){
    this.form.controls[control].setValidators([Validators.required]);
    this.form.controls[control].enable();
    this.form.controls[control].updateValueAndValidity();
  }
  clearControls(control: string) {
    this.form.controls[control].clearValidators();
    this.form.controls[control].setValue('');
    this.form.controls[control].disable();
    this.form.controls[control].updateValueAndValidity();
  }


  // changepage() {
  //   const bill = this.form.controls.bill;
  //   let page = "";
  //   if (bill.value == '1') {
  //     page = 'int06/11/1';
  //   } else if (bill.value == '2') {
  //     page = 'int06/11/2';
  //   } else if (bill.value == '3') {
  //     page = 'int06/11/3';
  //   } else if (bill.value == '4') {
  //     page = 'int06/11/4';
  //   }

  //   this.router.navigate([page], {
  //   });
  // }

}