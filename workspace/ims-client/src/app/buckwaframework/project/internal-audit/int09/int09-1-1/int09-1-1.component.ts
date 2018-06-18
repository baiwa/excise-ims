import { Component, OnInit } from '@angular/core';
import { TravelCost, TravelCosts } from '../../../../common/models';
import { AjaxService } from '../../../../common/services';
declare var $: any;
@Component({
  selector: 'app-int09-1-1',
  templateUrl: './int09-1-1.component.html',
  styleUrls: ['./int09-1-1.component.css']
})
export class Int0911Component implements OnInit {

  public allData: TravelCosts[];
  public data: TravelCosts;
  private $form: any;
  typeDocs: String[];
  topics: String[][];
  topic: String[];

  selectDoc: String;
  selectTop: String;

  selectedDoc: String;
  selectedTop: String;
  sent: boolean;


  constructor(private ajax: AjaxService) {
    this.data = new TravelCosts();
    this.allData = new Array<TravelCosts>();
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
    $('.ui.radio.checkbox')
      .checkbox()
      ;
  }

  onSelectDoc = event => {
    this.topic = this.topics[event.target.value];
    this.selectDoc = this.typeDocs[event.target.value];
  };

  onSelectTop = event => {
    this.selectTop = this.topic[event.target.value];
  };

  onSubmit = () => {
    // show form generate pdf
    this.sent = true;
    this.selectedTop = this.selectTop;
    var data: TravelCost = new TravelCost();
    const URL = "ia/int09/create";

    this.ajax.post(URL, JSON.stringify(data), function (res) {
      console.log(res);
    });

  };


  total(index) {
    const { allowance, accommodation, passage, outgoings } = this.allData[index];
    return allowance + accommodation + passage + outgoings;
  }

  selectedBox(e, index) {
    this.allData[index].checked = e.target.checked;
  }

  addData() {
    this.allData.push(this.data);
    this.clearData();
  }

  deleteData() {
    this.allData = this.allData.filter(obj => {
      return !obj.checked;
    });
  }

  editData(index: number) {
    this.data = this.allData[index];
  }

  clearData() {
    this.data = new TravelCosts();
  }


}
