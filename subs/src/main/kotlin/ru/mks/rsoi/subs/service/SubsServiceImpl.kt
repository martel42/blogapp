package ru.mks.rsoi.subs.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.subs.entity.Subs
import ru.mks.rsoi.subs.repository.SubsRepository

@Service
class SubsServiceImpl(
        val subsRepository: SubsRepository
): SubsService {
    @Transactional(readOnly = true)
    override fun getSubsById(id: Long): Subs =
            subsRepository.findById(id).orElseThrow { EntityNotFoundException ("Subs $id not found!") }

    @Transactional(readOnly = true)
    override fun getAllSubs(): List<Subs> =
            subsRepository.findAll()

    @Transactional
    override fun addOrEditSubs(subs: Subs) {
        subsRepository.save(subs)
    }

    @Transactional
    override fun deleteSubsById(id: Long) {
        subsRepository.deleteById(id)
    }

    @Transactional
    override fun deleteAllSubs() {
        subsRepository.deleteAll()
    }
}