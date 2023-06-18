package ru.mks.rsoi.subs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.subs.entity.Subs
import ru.mks.rsoi.subs.service.SubsService

@RestController
@CrossOrigin("http://localhost:8085/api/v1/subs")
@RequestMapping("api/v1/subs")
class SubsController(
        private val subsService: SubsService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm subs dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getSubsById(@PathVariable id: Long) : Subs =
            subsService.getSubsById(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllSubs() :List<Subs> =
            subsService.getAllSubs()


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addSubs(@RequestBody subsResponse: Subs) {
        subsService.addOrEditSubs(subsResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editSubs(@RequestBody subsResponse: Subs) {
        subsService.addOrEditSubs(subsResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteSubsById(@PathVariable id: Long) {
        subsService.deleteSubsById(id)
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteAllSubs() {
        subsService.deleteAllSubs()
    }

    @DeleteMapping("/plus/{uid}/{bid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteSubsPlus(@PathVariable uid: Long, @PathVariable bid: Long) {
        subsService.deleteSubsPlus(uid, bid)
    }
}