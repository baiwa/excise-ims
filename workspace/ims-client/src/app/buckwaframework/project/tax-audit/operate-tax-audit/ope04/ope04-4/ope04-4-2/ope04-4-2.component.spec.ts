import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Ope0442Component } from './ope04-4-2.component';

describe('Ope0442Component', () => {
  let component: Ope0442Component;
  let fixture: ComponentFixture<Ope0442Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Ope0442Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Ope0442Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
