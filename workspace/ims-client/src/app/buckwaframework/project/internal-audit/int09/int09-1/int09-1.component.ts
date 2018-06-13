import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-int09-1',
  templateUrl: './int09-1.component.html',
  styleUrls: ['./int09-1.component.css']
})
export class Int091Component implements OnInit {
  private showData: boolean = false;
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
