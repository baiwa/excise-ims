import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0442Service } from './ope04-4-2.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Utils } from 'helpers/utils';

@Component({
  selector: 'app-ope04-4-2',
  templateUrl: './ope04-4-2.component.html',
  styleUrls: ['./ope04-4-2.component.css'],
  providers:[Ope0442Service]
})
export class Ope0442Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการรับสินค้าสำเร็จรูป', route: '#' },
  ]

  id: string = "";

  constructor(
    private myService: Ope0442Service,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.id = this.route.snapshot.queryParams["id"];
    if (Utils.isNull(this.id)) this.router.navigate(['/ope04/4-1']);    
   }

  ngOnInit() {
  }

  ngAfterViewInit() {
    
    this.datatable(this.id);
  }
  datatable = (id: any) => {
    this.myService.datatable(id);
  }
}