import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-mgcontrol',
  templateUrl: './mgcontrol.component.html',
  styleUrls: ['./mgcontrol.component.css']
})
export class MgcontrolComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#context .menu .item')
      .tab({
        // special keyword works same as above
        context: 'parent'
      });
  }

}
