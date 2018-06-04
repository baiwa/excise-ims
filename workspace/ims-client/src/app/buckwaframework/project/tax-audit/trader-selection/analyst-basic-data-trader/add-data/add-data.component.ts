import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AjaxService} from '../../../../../common/services/ajax.service';
import { Excise } from '../../../../../common/models/excise';

@Component({
  selector: 'app-add-data',
  templateUrl: './add-data.component.html',
  styleUrls: ['./add-data.component.css']
})
export class AddDataComponent implements OnInit {

  id: string;
  excise: Excise;

  constructor(
    private route: ActivatedRoute,
    private service: AjaxService) {
    this.id = this.route.snapshot.queryParams['id'];
    this.excise = new Excise;
  }

  ngOnInit() {
    const url = `working/test/list/${this.id}`;
    this.service
      .get(url, console.log(`GET '/working/test/list/${this.id}'`), null)
      .then(
        res => {
          this.excise = res.json()[0];
        }
      );
  }

}
