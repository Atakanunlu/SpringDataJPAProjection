
# Spring Data JPA – Hibernate Playground 🧪

Bu repository, **Spring Data JPA**, **Hibernate**, **Entity ilişkileri**, **Projection türleri** ve **Persistence Context davranışlarını** öğrenmek ve denemek amacıyla oluşturulmuş **eğitim / çalışma (playground)** niteliğinde bir projedir.

> ❗ Bu bir “ürün” veya “tamamlanmış proje” değildir.
> Amaç: **JPA’nın nasıl çalıştığını gerçekten anlamak**.

---

## 🎯 Amaçlar

Bu çalışmada aşağıdaki konular **tek tek denenmiş ve gözlemlenmiştir**:

* Entity lifecycle (Transient / Managed / Detached / Removed)
* Owning side vs Inverse side
* OneToOne, OneToMany, ManyToOne, ManyToMany ilişkileri
* Interface-based & Class-based Projection
* JPQL (`select new`, `group by`, `fetch join`)
* Persistence Context ve `@Transactional` davranışı
* Cascade & orphanRemoval etkileri
* Dirty checking
* Lazy vs Eager loading
* Test üzerinden davranış gözlemi

---

## 🧱 Kullanılan Teknolojiler

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Lombok
* JUnit

---

## 📦 Domain Model (Özet)

**Entity’ler:**

* `Patient`
* `Doctor`
* `Appointment`
* `Insurance`
* `Department`

**Enum:**

* `BloodGroupType`

---

## 🔗 Entity İlişkileri

### Patient – Insurance

```java
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "patient_insurance", unique = true)
```

* **Owning side:** `Patient`
* **Inverse side:** `Insurance`
* `orphanRemoval = true` → insurance otomatik silinir

---

### Patient – Appointment

```java
@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
```

```java
@ManyToOne
@JoinColumn(nullable = false)
```

* **Owning side:** `Appointment`
* **Inverse side:** `Patient`

---

### Doctor – Appointment

```java
@OneToMany(mappedBy = "doctor")
```

```java
@ManyToOne(fetch = FetchType.LAZY)
```

* FetchType davranışı özellikle gözlemlendi

---

### Department – Doctor

```java
@OneToOne
@JoinColumn(nullable = false)
```

```java
@ManyToMany
```

---

## 📊 Projection Çalışmaları

### 1️⃣ Interface-based Projection

```java
public interface IPatientInfo {
    Long getId();
    String getName();
    String getEmail();
}
```

```java
@Query("SELECT p.id AS id, p.name AS name, p.email AS email FROM Patient p")
List<IPatientInfo> getAllPatientInfo();
```

✔️ Avantaj: Hafif, hızlı
❌ Dezavantaj: Constructor / logic yok

---

### 2️⃣ Class-based Projection (`select new`)

```java
public class CPatientInfo {
    private final Long id;
    private final String name;
}
```

```java
@Query("SELECT new ...CPatientInfo(p.id, p.name) FROM Patient p")
List<CPatientInfo> getAllPatientInfoC();
```

✔️ Avantaj: Type-safe, logic eklenebilir
❌ Dezavantaj: Constructor zorunlu

---

### 3️⃣ Aggregation + DTO

```java
@Query("""
select new BloodGroupStats(p.bloodGroup, COUNT(p))
from Patient p
group by p.bloodGroup
order by COUNT(p) DESC
""")
```

---

## 🔄 Persistence Context Deneyi

```java
@Transactional
public void testPatientTransaction() {
    Patient p1 = patientRepository.findById(1L).orElseThrow();
    Patient p2 = patientRepository.findById(1L).orElseThrow();

    System.out.println(p1 == p2); // true
}
```

✔️ Aynı transaction içinde **aynı entity = aynı referans**

---

## ✏️ Dirty Checking & Update

```java
@Transactional
@Modifying
@Query("UPDATE Patient p set p.name = :name where p.id = :id")
int updatePatientNameWithId(...)
```

* JPQL update
* Persistence context flush davranışı gözlemlendi

---

## 🚀 Fetch Join Kullanımı

```java
@Query("select p from Patient p LEFT JOIN FETCH p.appointments")
List<Patient> getAllPatientsWithAppointments();
```

✔️ N+1 problemine karşı çözüm
✔️ Eager gibi ama kontrollü

---

## 🧪 Test Yaklaşımı

* Unit test değil
* **Davranış gözlem testleri**
* Amaç: “Ne oluyor?” sorusuna cevap bulmak

Örnek:

* Cascade silme
* Orphan removal
* Transaction sınırı
* Lazy loading exception

---

## ⚙️ Öne Çıkan Anotasyonlar (Gruplu)

### JPA / Hibernate

* `@Entity`
* `@Id`
* `@GeneratedValue`
* `@Enumerated`
* `@CreationTimestamp`
* `@OneToOne`
* `@OneToMany`
* `@ManyToOne`
* `@ManyToMany`
* `@JoinColumn`
* `@Column`

### Spring Data JPA

* `@Query`
* `@Modifying`
* `@Transactional`
* `JpaRepository`

### Lombok

* `@Getter / @Setter`
* `@Builder`
* `@NoArgsConstructor`
* `@AllArgsConstructor`
* `@ToString`

---
### ER-DIAGRAM

<img width="1025" height="940" alt="Ekran görüntüsü 2026-01-17 213500" src="https://github.com/user-attachments/assets/964449c3-1647-4c9c-8821-b70f1960842c" />


---


