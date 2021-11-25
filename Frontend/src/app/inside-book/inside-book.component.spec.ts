import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsideBookComponent } from './inside-book.component';

describe('InsideBookComponent', () => {
  let component: InsideBookComponent;
  let fixture: ComponentFixture<InsideBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsideBookComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsideBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
