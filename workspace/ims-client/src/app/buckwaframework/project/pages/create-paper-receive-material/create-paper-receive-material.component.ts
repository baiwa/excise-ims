import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-receive-material',
  templateUrl: './create-paper-receive-material.component.html',
  styleUrls: ['./create-paper-receive-material.component.css']
})
export class CreatePaperReceiveMaterialComponent implements OnInit {

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
