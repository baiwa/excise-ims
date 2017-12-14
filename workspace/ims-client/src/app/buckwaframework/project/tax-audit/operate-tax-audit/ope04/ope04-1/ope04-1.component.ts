import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope04-1',
  templateUrl: './ope04-1.component.html',
  styleUrls: ['./ope04-1.component.css']
})
export class Ope041Component implements OnInit {

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
