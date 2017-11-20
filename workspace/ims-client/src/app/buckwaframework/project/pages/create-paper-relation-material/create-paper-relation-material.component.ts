import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-relation-material',
  templateUrl: './create-paper-relation-material.component.html',
  styleUrls: ['./create-paper-relation-material.component.css']
})
export class CreatePaperRelationMaterialComponent implements OnInit {

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
