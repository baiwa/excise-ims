import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int07-5-3',
  templateUrl: './int07-5-3.component.html',
  styleUrls: ['./int07-5-3.component.css']
})
export class Int0753Component implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

}
