/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.core.model;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.exoplatform.social.client.api.model.Activity;
import org.exoplatform.social.client.api.model.Comment;
import org.exoplatform.social.client.api.model.Identity;
import org.exoplatform.social.client.api.net.SocialHttpClient.POLICY;
import org.exoplatform.social.client.api.service.ServiceException;
import org.exoplatform.social.client.core.util.SocialHttpClientSupport;
import org.exoplatform.social.client.core.util.SocialJSONDecodingSupport;
import org.json.simple.parser.ParseException;

/**
 * Implementation of {@link Comment}.
 *
 * @author <a href="http://hoatle.net">hoatle (hoatlevan at gmail dot com)</a>
 * @since May 26, 2011
 */
public class CommentImpl extends ModelImpl implements Comment {

  /**
   * Constructor without any params.
   */
  public CommentImpl() {

  }

  /**
   * Constructor.
   *
   * @param id         the comment id
   * @param identityId the identity id
   * @param activityId the activity id
   * @param postedTime the posted time
   */
  public CommentImpl(String id, String identityId, String activityId, Long postedTime, String createdAt) {
    setId(id);
    setIdentityId(identityId);
    setActivityId(activityId);
    setPostedTime(postedTime);
    setCreatedAt(createdAt);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return getFieldAsString(Field.ID.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setId(String id) {
    setField(Field.ID.toString(), id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIdentityId() {
    return getFieldAsString(Field.IDENTITY_ID.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setIdentityId(String identityId) {
    setField(Field.IDENTITY_ID.toString(), identityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getActivityId() {
    return getFieldAsString(Field.ACTIVITY_ID.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setActivityId(String activityId) {
    setField(Field.ACTIVITY_ID.toString(), activityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return getFieldAsString(Field.TEXT.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setText(String content) {
    setField(Field.TEXT.toString(), content);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long getPostedTime() {
    return (Long) getField(Field.POSTED_TIME.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPostedTime(Long postedTime) {
    setField(Field.POSTED_TIME.toString(), postedTime);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getCreatedAt() {
    return getFieldAsString(Field.CREATED_AT.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCreatedAt(String createdAt) {
    setField(Field.CREATED_AT.toString(), createdAt);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Activity getActivity() {
    Activity activity = null;
    try {
      String BASE_URL = SocialHttpClientSupport.buildCommonRestPathFromContext(true);
      String requestURL = BASE_URL + "activity/" + this.getActivityId() + ".json";
      HttpResponse response = SocialHttpClientSupport.executeGet(requestURL, POLICY.BASIC_AUTH);
      activity = SocialJSONDecodingSupport.parser(Activity.class, response);
    } catch (IOException e) {
      throw new ServiceException(CommentImpl.class, "IOException when reads Json Content.", e);
    } catch (ParseException e) {
      throw new ServiceException(CommentImpl.class, "ParseException when reads Json Content.", e);
    }
    return activity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Identity getIdentity() {
    Identity identity = null;
    try {
      String BASE_URL = SocialHttpClientSupport.buildCommonRestPathFromContext(true);
      String requestURL = BASE_URL + "identity/" + this.getIdentityId() + ".json";
      HttpResponse response = SocialHttpClientSupport.executeGet(requestURL, POLICY.BASIC_AUTH);
      identity = SocialJSONDecodingSupport.parser(Identity.class, response);
    } catch (IOException e) {
      throw new ServiceException(CommentImpl.class, "IOException when reads Json Content.", e);
    } catch (ParseException e) {
      throw new ServiceException(CommentImpl.class, "ParseException when reads Json Content.", e);
    }
    return identity;
  }
}
