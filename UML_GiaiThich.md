# Sơ đồ UML
![Sơ đồ bài toán](https://www.planttext.com/api/plantuml/png/p5NDZjem4BxdAQmz1GbU8AhGfYn8bTWVGUYbgbQ3Oy1T_wIsKsbRxPFrq4VgAzGEIR1De3qsXoWPp_Tvvfln-FVFNxVQW38rPqbI0Nw1FK4tN9Q4tQ0V2R8FPg0rUW_YfL0WD613fMZNt3D1Iuc1tO4XgBGlRw6KzPOH15pMmdZsjL5Kx559D5QqSdpz8eftIXrdTjfZtD9bBVlOfybQwf77joV6uTAHHn8kvmsXSpXIHk0JlNCvRk2v67aq3IwIhpKmdSeCFMw_46oiprPAHi3F-03wCBEIEmRQ2lMMl7a5gK1fehgAM1-YTe19ezNQs8Gu4UQfSV8gWr23MoZ9KqO5wTN9Of5ZoD9wwnwdhN9KctT7z7JK_aOSqpRkg6dwy6RhZss4b5UiCUd_Ce50U2KnqLggyzDm2hlp2G_5q0ve6nfqrFVdd6cCk12xt_6soN7DB5CX3R0htHqwVX3tKfX3ojqxGytFtr4BgMRsUo_LQuhR3wzE7ZfK5WImr-_ach1vwnBeQiULeRWR8Qlv-d6pcitNNdmjSxgZZ3m0xxjcV6mDbGrrdGtrcwIbjn6MZDaJkwuv1vjRtmanlBFx366gjQ7HD7KpQ6EjGvcvjO9oOeNbOEn9HtVpHRvP5iztUJ5_VigBOhvwY1k-6N5kLDfSFVc79jwk8Uu1dbCumS8aE50H9d5lBNbLCMhB7bu923dl5DHUTuoVFf_fSs4wJ2RJeG8bmwrnvjqJ_IkWBs1ouLHt27_SN8czgsqINvd1OYpUMLXTm4boIqJfxkS_0000__y30000)

# Giải thích cách sử dụng để giải quyết các vấn đề về: SRP, Cohesion, Coupling

Trong thiết kế phần mềm, các nguyên tắc **SRP (Single Responsibility Principle)**, **Cohesion**, và **Coupling** giúp xây dựng mã nguồn dễ duy trì, mở rộng và có thể kiểm thử một cách hiệu quả. Dưới đây là cách tác áp dụng chúng trong mã nguồn.

## 1. SRP (Single Responsibility Principle)
**SRP** nói rằng mỗi lớp (hoặc module) chỉ nên có một lý do để thay đổi, nghĩa là mỗi lớp chỉ nên thực hiện một nhiệm vụ cụ thể.

Trong mã nguồn của bạn:
- **Lớp `BankTransaction`** chỉ đại diện cho một giao dịch ngân hàng và chứa các thuộc tính liên quan như `date`, `amount`, `description`. Nó chỉ có nhiệm vụ lưu trữ và cung cấp thông tin về giao dịch, không thực hiện bất kỳ hành động nào liên quan đến xử lý dữ liệu hay tính toán. Điều này giúp lớp này có trách nhiệm duy nhất.
- **Lớp `BankStatementCSVParser`** chỉ chịu trách nhiệm phân tích dữ liệu từ tệp CSV thành các đối tượng `BankTransaction`. Mọi logic phân tích dữ liệu đều được dồn vào lớp này, đảm bảo rằng việc phân tích và xử lý dữ liệu CSV không bị lẫn vào các nhiệm vụ khác.
- **Lớp `BankStatementProcessor`** thực hiện các phép tính với các giao dịch ngân hàng (như tính tổng số tiền, tổng theo tháng, tổng theo danh mục). Nó không chịu trách nhiệm phân tích dữ liệu hay hiển thị kết quả. Điều này giúp đảm bảo lớp này chỉ có một lý do thay đổi là khi thay đổi cách tính toán liên quan đến giao dịch ngân hàng.

Bằng cách tách biệt các chức năng này, đảm bảo rằng mỗi lớp trong hệ thống có một trách nhiệm rõ ràng, dễ duy trì và mở rộng.

## 2. Cohesion (Độ kết dính)
**Cohesion** đo lường mức độ các thành phần trong một lớp hoặc module có liên quan chặt chẽ với nhau. Một lớp có độ kết dính cao khi tất cả các phương thức và thuộc tính của nó đều tập trung vào một nhiệm vụ cụ thể.

Trong mã nguồn của bạn:
- **Lớp `BankTransaction`** có độ kết dính cao vì nó chỉ chứa các thuộc tính liên quan đến một giao dịch ngân hàng (`date`, `amount`, `description`) và cung cấp các phương thức truy xuất thông tin về giao dịch. Mọi thành phần trong lớp này đều liên quan trực tiếp đến đối tượng giao dịch ngân hàng.
- **Lớp `BankStatementCSVParser`** có độ kết dính cao vì tất cả các phương thức của nó đều tập trung vào việc phân tích tệp CSV thành các đối tượng `BankTransaction`. Tất cả các hành động trong lớp này đều liên quan đến việc xử lý dữ liệu từ CSV, không làm điều gì khác.
- **Lớp `BankStatementProcessor`** có độ kết dính cao vì tất cả các phương thức của nó đều liên quan đến việc tính toán các giá trị tổng hợp từ danh sách các giao dịch ngân hàng. Mỗi phương thức đều thực hiện các phép tính liên quan trực tiếp đến dữ liệu của các giao dịch ngân hàng.

Việc nhóm các phương thức và thuộc tính có liên quan chặt chẽ với nhau giúp tăng độ kết dính của các lớp, giúp mã dễ đọc và dễ bảo trì hơn.

## 3. Coupling (Độ liên kết)
**Coupling** đề cập đến mức độ phụ thuộc giữa các lớp hoặc module. Mục tiêu là giảm thiểu sự phụ thuộc giữa các lớp để các phần của ứng dụng có thể thay đổi mà không ảnh hưởng đến nhau.

Trong mã nguồn:
- **Lớp `BankStatementAnalyzer`** có sự phụ thuộc thấp vào các lớp khác. Nó chỉ sử dụng `BankStatementParser` và `BankStatementProcessor` để thực hiện công việc phân tích và xử lý dữ liệu. Tuy nhiên, bạn có thể dễ dàng thay đổi cách phân tích tệp (bằng cách thay đổi parser) hoặc cách xử lý các giao dịch (bằng cách thay đổi processor) mà không ảnh hưởng đến các phần còn lại của hệ thống.
- **Lớp `BankStatementCSVParser`** chỉ phụ thuộc vào lớp `DateUtil` để chuyển đổi chuỗi ngày tháng thành đối tượng `LocalDate`. Điều này giúp giảm sự phụ thuộc vào các thành phần khác trong hệ thống.
- **Lớp `BankStatementProcessor`** chỉ phụ thuộc vào `BankTransaction`, giúp nó không bị phụ thuộc vào các lớp khác ngoài lớp mô hình giao dịch.

Giảm thiểu sự phụ thuộc giữa các lớp giúp hệ thống dễ dàng mở rộng và thay đổi mà không gây ra sự cố. Ví dụ, nếu bạn muốn thay đổi cách phân tích tệp CSV hoặc thêm một loại phân tích mới, bạn chỉ cần thay đổi hoặc thêm một parser mà không cần thay đổi `BankStatementAnalyzer` hoặc các phần khác của hệ thống.

## Tóm lại các định nghĩa ta hiểu:
- **SRP**: Mỗi lớp thực hiện một nhiệm vụ cụ thể và chỉ có một lý do để thay đổi.
- **Cohesion**: Mỗi lớp có độ kết dính cao vì tất cả các phương thức và thuộc tính trong lớp đều liên quan chặt chẽ đến một nhiệm vụ duy nhất.
- **Coupling**: Các lớp phụ thuộc ít vào nhau, giúp mã dễ dàng thay đổi và mở rộng mà không ảnh hưởng đến các phần khác của hệ thống.

Việc tuân thủ các nguyên tắc này giúp mã dễ duy trì, dễ mở rộng, và dễ kiểm thử hơn.
