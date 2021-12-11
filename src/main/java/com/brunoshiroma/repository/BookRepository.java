package com.brunoshiroma.repository;

import com.brunoshiroma.entity.BookEntity;
import com.brunoshiroma.entity.CategoryEntity;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class BookRepository implements PanacheRepository<BookEntity> {

    public Uni<BookEntity> create(BookEntity book){
        return Panache.withTransaction(() -> {
            // logic to check if the category is a insert or a update
            Multi<Uni<PanacheEntityBase>> categoryEntityUni = Multi.createFrom().emitter(multiEmitter -> {
                for( int i = 0; i < book.getCategories().size(); i++){
                    final var index = i;
                    var category = book.getCategories().get(index);
                    multiEmitter.emit(
                    CategoryEntity.findById(category.getCategoryId())
                            .onItemOrFailure().call((entity, exception) ->{
                                if (entity == null){// dont exists insert.
                                    category.setCategoryId(null);
                                    return CategoryEntity.persist(category).onItemOrFailure().invoke((categoryEntity, error)->{
                                        book.getCategories().set(index, category);
                                    });
                                } else {
                                    // exists, only update the ref on books.
                                    book.getCategories().set(index, (CategoryEntity) entity);
                                    return Uni.createFrom().item(entity);
                                }
                            })
                    );
                }
                multiEmitter.complete();
            });

            return categoryEntityUni
                    .onItem().call(Uni::log)
                    .onCompletion().call(()-> BookEntity.persist(book))//persist the book
                    .onItem().ignoreAsUni().map(panacheEntityBaseUni -> book);
        });
    }

}
