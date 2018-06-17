import { Component, OnInit } from '@angular/core';
import { TravelCosts } from '../../../../common/models';

@Component({
  selector: 'app-int09-1-1',
  templateUrl: './int09-1-1.component.html',
  styleUrls: ['./int09-1-1.component.css']
})
export class Int0911Component implements OnInit {

  public allData: TravelCosts[];
  public data: TravelCosts;

  constructor() {
    this.data = new TravelCosts();
    this.allData = new Array<TravelCosts>();
  }

  ngOnInit() {
  }

  onSubmitt() {
    alert("submit...~");
  }

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
    this.allData = this.allData.filter( obj => {
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
