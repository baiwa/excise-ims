
import { Component, OnInit } from '@angular/core';
import { TextDateTH } from '../../../../common/helper/datepicker';
import { AjaxService } from '../../../../common/services';
declare var $: any;
@Component({
  selector: 'app-int09-1',
  templateUrl: './int09-1.component.html',
  styleUrls: ['./int09-1.component.css']
})
export class Int091Component implements OnInit {
  private $form: any;
  private showData: boolean = false;
  typeDocs: String[];
  topics: String[][];
  topic: String[];

  selectDoc: String;
  selectTop: String;

  selectedDoc: String;
  selectedTop: String;

  sent: boolean;

  constructor() {
    this.typeDocs = [
      'ทั่วไป',
      'วิชาการ',
      'อำนวยการ',
      'บริหาร',
    ];
    this.topics = [
      [
        'ปฏิบัติงาน',
        'ชำนาญงาน',
        'อาวุโส',
        'ทักษะพิเศษ',

      ],
      [
        'ปฏิบัติการ',
        'ชำนาญการ',
        'ชำนาญการพิเศษ',
        'เชี่ยวชาญ',
        'ทรงคุณวุฒิ',
      ],
      [

        'ระดับต้น',
        'ระดับสูง',

      ], [

        'ระดับต้น',
        'ระดับสูง',

      ]
    ];
    this.topic = [];
    this.sent = false; // false
    this.selectedTop = ''; // ''
  }

  ngOnInit() {
    $('#example2').calendar({
      type: 'date',
      text: TextDateTH
    });
    $('#example3').calendar({
      type: 'date',
      text: TextDateTH
    });
    this.$form = $('#int09-1');
  }
  onSelectDoc = event => {
    this.topic = this.topics[event.target.value];
    this.selectDoc = this.typeDocs[event.target.value];
  };

  onSelectTop = event => {
    this.selectTop = this.topic[event.target.value];
  };

  onSubmitt = () => {
    // show form generate pdf
    this.sent = true;
    this.selectedTop = this.selectTop;
  };

  onDiscard = event => {
    // hide form generate pdf
    this.sent = event;
  };
  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
  onSubmit = () => {
    let allFields = this.$form.form('get values');
    console.log("allFields", allFields);

    const URL = AjaxService.CONTEXT_PATH + "/ia/int09/creaet";
    console.log(URL);
    var parameter = {};

    $.post(URL,allFields,
      function (data) {
        console.log(data);
      }).fail(function () {

        console.log("error");
      });
  };

  saveT

}

