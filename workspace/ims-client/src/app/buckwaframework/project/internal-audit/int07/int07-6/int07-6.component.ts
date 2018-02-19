import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-6',
  templateUrl: './int07-6.component.html',
  styleUrls: ['./int07-6.component.css']
})
export class Int076Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }
 
  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}
