import { Component, OnInit } from '@angular/core';
import { TravelCostHeader, TravelCostDetail } from '../../../../common/models';
import { AjaxService } from '../../../../common/services';
import { Prices } from '../../../../common/helper/travel';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { TextDateTH, formatter } from '../../../../common/helper/datepicker';
declare var $: any;
@Component({
  selector: 'app-int09-1-1',
  templateUrl: './int09-1-1.component.html',
  styleUrls: ['./int09-1-1.component.css']
})
export class Int0911Component implements OnInit {

  public status: string;
  public id: number;

  public hdr: TravelCostHeader;
  public detail: TravelCostDetail[];
  public data: TravelCostDetail;
  typeDocs: string[];
  topics: string[][];
  topic: string[];

  selectDoc: string;
  selectTop: string;

  selectedDoc: string;
  selectedTop: string;
  sent: boolean;



  constructor(private ajax: AjaxService ,  private router: Router) {
  
    this.status = 'create';
    this.hdr = new TravelCostHeader();
    this.detail = new Array<TravelCostDetail>();
    this.data = new TravelCostDetail();
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
    $('.ui.radio.checkbox').checkbox();
    $('#example2').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
    $('#example3').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
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
    const {
      workSheetHeaderName,
      departmentName,
      startDate,
      endDate,
      description
    } = this.hdr;
    var data: TravelCostHeader = {
      workSheetHeaderId: 0,
      workSheetHeaderName: workSheetHeaderName,
      departmentName: departmentName,
      startDate: startDate,
      endDate: endDate,
      description: description,
      createdBy: '',
      createdDatetime: null,
      updateBy: '',
      updateDatetime: null,
      Detail: this.detail
    };

    const URL = "ia/int09/create";
    var router = this.router;
    this.ajax.post(URL, data, function (res) {      
      console.log(res.json());
      router.navigate(['/int09-1']);
    });

  };

  getPrice(index, what) {
    const { category, degree, allowanceDate, rentDate } = this.detail[index];
    const num = what === 'allowance' ? allowanceDate : rentDate;
    return Prices(category, degree, what) * num;
  }

  totalCost(index) {
    const { restType, travelCost, otherCost } = this.detail[index];
    let total:number = 0;
    total += this.getPrice(index, 'allowance');
    total += this.getPrice(index, restType);
    total += parseFloat(travelCost.toString());
    total += parseFloat(otherCost.toString());
    return total;
  }

  selectedBox(e, index) {
    this.detail[index].checked = e.target.checked;
  }

  manageDate() {
    if (this.status === 'create') {
      this.detail.push(this.data);
      this.clearData();
    } else {
      const data = this.data;
      this.detail[this.id] = data;
      this.status = 'edit';
    }
  }

  deleteData() {
    this.detail = this.detail.filter(obj => {
      return !obj.checked;
    });
  }

  editData(index: number) {

    // change `status` and `id`
    this.id = index;
    this.status = 'edit';

    const data: any = this.detail[index];
    this.data = new TravelCostDetail();
    this.data = data;

  }

  clearData() {
    if (this.status === 'create'){
      this.data = new TravelCostDetail();
    } else {
      this.data = new TravelCostDetail();
      this.status = 'create';
    }
  }

}
