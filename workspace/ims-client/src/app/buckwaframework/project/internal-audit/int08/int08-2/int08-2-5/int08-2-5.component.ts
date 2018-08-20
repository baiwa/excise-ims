import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-int08-2-5',
  templateUrl: './int08-2-5.component.html',
  styleUrls: ['./int08-2-5.component.css']
})
export class Int0825Component implements OnInit {
  
  showData: boolean = false;
  
  datas: Condition[];
  constructor() { 
    this.datas = [];
    for (let i = 0; i < 3; i++) {
      this.datas.push(new Condition());
    }
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }
  ngAfterViewInit() {
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  modalConditionRL() {
    $("#ConditionRL").modal("show");
  }

  closeConditionRL() {
    $("#ConditionRL").modal("hide");
  }

  modalCondition() {
    $("#Condition").modal("show");
  }

  addRow() {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index) {
    this.datas.splice(index, 1);
  }
}

class Condition {
  [x: string]: any;
  seq: any;
  conditionRick: any;
  value1: any;
  value2: any;
  valueRL: any;
  convertValue: any;
  color: any;
}
