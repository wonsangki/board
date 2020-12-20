package com.wonsang.web.modules.article.model;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  int countByDelYnAndContentsContaining(String delYn, String contents);
  int countByDelYnAndAccount_NicknameContaining(String delYn, String accountNickname);
  int countByDelYnAndTitleContaining(String delYn, String title);
  int countByDelYnAndArticleHashtagList(String delYn, String articleHashtagList);
  int countByDelYnAndTitleContainingOrAccount_NicknameContainingOrContentsContaining(String delYn, String title, String accountNickname, String contents);

  Page<Article> findAllByDelYnAndContentsContaining(Pageable pageable, String delYn, String contents);
  Page<Article> findAllByDelYnAndAccount_NicknameContaining(Pageable pageable, String delYn, String accountNickname);
  Page<Article> findAllByDelYnAndTitleContaining(Pageable pageable, String delYn, String title);
  Page<Article> findAllByDelYnAndArticleHashtagList(Pageable pageable, String delYn, String articleHashtagList);
  Page<Article> findAllByDelYnAndTitleContainingOrAccount_NicknameContainingOrContentsContaining(Pageable pageable,String delYn, String title, String accountNickname, String contents);


  Article findById(Long id);


}
