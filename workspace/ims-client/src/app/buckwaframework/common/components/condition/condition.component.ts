import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { AjaxService } from "../../../common/services/ajax.service";
import { MessageBarService } from "../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";


declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'condition',
  templateUrl: './condition.component.html',
  styleUrls: ['./condition.component.css']
})
export class ConditionComponent implements OnInit {

  datas: Condition[];
  @Input() riskId: any;
  @Input() riskType: any;
  @Input() page: any;
  @Output() out: EventEmitter<number> = new EventEmitter<number>();
  @Output() has: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private messageBarService: MessageBarService,
    private ajax: AjaxService,
  ) { }

  ngOnInit() {
    this.datas = [];
    console.log("ngOnInit");
    var url = "ia/condition/findConditionByParentId";
    this.ajax.post(url, { parentId: this.riskId, riskType: this.riskType, page: this.page }, res => {
      var data = res.json();
      if (data.length > 0) {
        this.has.emit(true);
      } else {
        this.has.emit(false);
      }
      data.forEach(element => {
        this.datas.push(element);
      });
      if (this.datas.length < 3) {
        for (let i = 0; i < 3; i++) {
          this.datas.push(new Condition());
        }
      }
    }, errRes => {
      if (this.datas.length < 3) {
        for (let i = 0; i < 3; i++) {
          this.datas.push(new Condition());
        }
      }
    });
  }

  addRow() {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index) {
    if (this.datas.length > 3) {
      this.datas.splice(index, 1);
    } else {
      this.messageBarService.errorModal("เงื่อนไขต้องมีอย่างน้อย 3 เงื่อนไข");
    }
  }

  saveCondition(e) {
    e.preventDefault();
    console.log("saveCondition");
    var message = "";
    var i = 1;
    this.datas.forEach(element => {
      i++;
      element.parentId = this.riskId;
      // parentId: any = '';
      // condition: any = '';
      // value1: any = '';
      // value2: any = '';
      // valueRL: any = '';
      // convertValue: any = '';
      // color: any = '';
    });
    if (i - 1 == this.datas.length) {
      var url = "ia/condition/insertCondition";
      let data = [];
      this.datas.forEach(element => {
        element.parentId = this.riskId;
        element.riskType = this.riskType;
        element.page = this.page;
        element.conditionId = element.conditionId == '' ? null : element.conditionId;
        element.condition = element.condition == '' ? null : element.condition;
        element.value1 = element.value1 == '' ? null : element.value1;
        element.value2 = element.value2 == '' ? null : element.value2;
        element.valueRl = element.valueRl == '' ? null : element.valueRl;
        element.color = element.color == '' ? null : element.color;
        data.push(element);
      });
      this.ajax.post(url, { condition: data }, res => {
        this.messageBarService.successModal("ดำเนินการเพิ่มเงื่อนไขสำเร็จ");
        this.has.emit(true);
        this.out.emit(this.riskId);
      });
    }
  }

  cancel() {
    this.out.emit(this.riskId);
  }

  changeCondution(i) {
    switch (this.datas[i].condition) {
      case "<>":
        $('#value1' + i).prop("disabled", false);
        $('#value2' + i).prop("disabled", false);
        $('#value2' + i).on('change', (e) => {
          $('#value2' + i).attr({
            min: $('#value1' + i).val() | 0
          });
          $('#value1' + i).attr({
            max: e.target.value
          });
        });
        $('#value1' + i).on('change', (e) => {
          $('#value2' + i).attr({
            min: e.target.value | 0
          });
          $('#value1' + i).attr({
            max: $('#value2' + i).val()
          });
        });
        break;
      case ">":
        $('#value1' + i).prop("disabled", false);
        $('#value2' + i).prop("disabled", true);
        break;
      case "<":
        $('#value1' + i).prop("disabled", false);
        $('#value2' + i).prop("disabled", true);
        break;
    }
  }

  isEmpty(value) {
    return value == null || value == undefined || value == '';
  }



}

class Condition {
  conditionId: any;
  parentId: any;
  condition: any;
  value1: any;
  value2: any;
  valueRl: any;
  convertValue: any;
  color: any;
  riskType: any = '';
  page: any;
}
