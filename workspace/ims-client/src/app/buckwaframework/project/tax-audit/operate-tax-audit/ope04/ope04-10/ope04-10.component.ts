import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope04-10',
  templateUrl: './ope04-10.component.html',
  styleUrls: ['./ope04-10.component.css']
})
export class Ope0410Component implements OnInit {

  public showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }


}
