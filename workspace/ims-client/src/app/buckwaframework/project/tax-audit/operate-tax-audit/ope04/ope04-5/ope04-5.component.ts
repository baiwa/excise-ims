import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope04-5',
  templateUrl: './ope04-5.component.html',
  styleUrls: ['./ope04-5.component.css']
})
export class Ope045Component implements OnInit {

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
