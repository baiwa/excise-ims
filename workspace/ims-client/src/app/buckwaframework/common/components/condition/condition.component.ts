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
  @Output() out: EventEmitter<number> = new EventEmitter<number>();

  constructor(private messageBarService: MessageBarService,
    private ajax: AjaxService,
  ) { }

  ngOnInit() {
    this.datas = [];
    console.log("ngOnInit");
    var url = "ia/condition/findConditionByParentId";
    this.ajax.post(url, { parentId: this.riskId }, res => {
      var data = res.json();
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
  saveCondition() {
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
      this.datas.forEach(element => {
        element.parentId = this.riskId;
        element.conditionId = element.conditionId == '' ? null : element.conditionId;
        element.condition = element.condition == '' ? null : element.condition;
        element.value1 = element.value1 == '' ? null : element.value1;
        element.value2 = element.value2 == '' ? null : element.value2;
        element.valueRL = element.valueRL == '' ? null : element.valueRL;
        element.color = element.color == '' ? null : element.color;
        this.ajax.post(url, element, res => {

        });

      });
      this.messageBarService.successModal("ดำเนินการเพิ่มเงือนใขสำเร็จ");
      this.out.emit(this.riskId);

    }
  }
  cancel() {
    this.out.emit(this.riskId);
  }


  changeCondution(i) {
    console.log($('#condition' + i).val());
    if ($('#condition' + i).val() == "ระหว่าง") {
      $('#value1' + i).prop("disabled", false);
      $('#value2' + i).prop("disabled", false);
    } else if ($('#condition' + i).val() == "มากกว่า") {
      $('#value1' + i).prop("disabled", false);
      $('#value2' + i).prop("disabled", true);
    } else if ($('#condition' + i).val() == "น้อยกว่า") {
      $('#value1' + i).prop("disabled", false);
      $('#value2' + i).prop("disabled", true);
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
  valueRL: any;
  convertValue: any;
  color: any;
}
